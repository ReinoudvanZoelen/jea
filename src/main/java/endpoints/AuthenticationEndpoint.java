package endpoints;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
    IPlayerDal playerService;

    @Inject
    JwtService jwtService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postJWT(Authentication authentication) {

        try {
            Player player = playerService.authenticate(authentication.getEmailAddress(), authentication.getPassword());
            if (player != null) {
                String token = jwtService.createJwt(player);
                // NewCookie cookie = new NewCookie("access_token", token);

                // JsonObject jsonObject = new JsonObject();
                // jsonObject.put("access_token", token);

                return Response.ok().entity(token).build();
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
            // Fetch the auth code
            OAuth2AccessToken authtoken = googleService.getService().getAccessToken(code);

            // Fetch the information for this user using the auth code
            OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.googleapis.com/oauth2/v2/userinfo");
            googleService.getService().signRequest(authtoken, request);
            com.github.scribejava.core.model.Response response = googleService.getService().execute(request);

            // Map JSON string to Object
            ObjectNode node = new ObjectMapper().readValue(response.getBody(), ObjectNode.class);

            if (node.has("email")) {
                String email = node.get("email").asText();
                Player player = playerService.getByEmailAddress(email);

                String token = "";
                if (player != null) {
                    // This google account exists in our database
                    token = jwtService.createJwt(player);
                } else {
                    // This google account does not exist in our database
                }

                return Response.status(Response.Status.ACCEPTED).entity(token).build();
            }

            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getStackTrace()).build();
        }
    }

}
