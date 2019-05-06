package endpoints;

import java.net.URI;

import javax.inject.Inject;
import javax.json.JsonString;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;

import datalayer.IPlayerDal;
import entities.Player;
import models.Authentication;
import services.GoogleService;
import services.JwtService;

@Produces(MediaType.APPLICATION_JSON)
@Path("/auth")
public class AuthenticationEndpoint {
    @Inject
    GoogleService googleService;

    @Inject
    IPlayerDal service;

    @Inject
    JwtService jwtService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postJWT(Authentication authentication) {

        try {
            Player player = service.authenticate(authentication.getEmailAddress(), authentication.getPassword());
            if (player != null) {
                String token = jwtService.createJwt(player.getEmailAddress(), player.getRole());
                NewCookie cookie = new NewCookie("access_token", token);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("access_token", token);

                return Response.ok().cookie(cookie).entity(jsonObject.toString()).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Path("/google")
    public Response getGoogleAuth() {
        String path = googleService.getService().getAuthorizationUrl();
        return Response.temporaryRedirect(URI.create(path)).build();
    }

    @GET
    @Path("/google/authenticate")
    public Response getAuthenticateGoogle(@QueryParam("code") String code) {
        try {
            OAuth2AccessToken token = googleService.getService().getAccessToken(code);

            OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.googleapis.com/oauth2/v2/userinfo");
            googleService.getService().signRequest(token, request);
            com.github.scribejava.core.model.Response response = googleService.getService().execute(request);
            return Response.status(Response.Status.ACCEPTED).entity(response.getBody()).build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getStackTrace()).build();
        }
    }

}
