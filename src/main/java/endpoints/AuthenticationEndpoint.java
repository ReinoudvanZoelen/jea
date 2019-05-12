package endpoints;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import datalayer.IPlayerDal;
import entities.Player;
import interceptors.MethodTimingInterceptor;
import models.Authentication;
import models.GoogleAuthentication;
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
    @Interceptors(MethodTimingInterceptor.class)
    public Response postJWT(Authentication authentication) {

        try {
            Player player = playerService.authenticate(authentication.getEmailAddress(), authentication.getPassword());
            if (player != null) {
                String token = jwtService.createJwt(player);

                // Create JSON of JWT
                Gson gson = new Gson();
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("token", token);

                return Response.ok().entity(gson.toJson(jsonObject)).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Path("/google")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postGoogleAuthentication(GoogleAuthentication googleAuthentication) {
        boolean isVerified = this.googleService.verifyGoogleAuthentication(googleAuthentication);

        if (isVerified) {
            Player player = playerService.getByEmailAddress(googleAuthentication.getEmail());

            if (player != null) {
                // This google account exists in our database
            } else {
                // This google account does not exist in our database
                player = new Player();
                player.setEmailAddress(googleAuthentication.getEmail());
                player.setRole("USER");

                playerService.add(player);
                player = playerService.getByEmailAddress(googleAuthentication.getEmail());
            }

            if (player != null) {
                String token = jwtService.createJwt(player);
                return Response.status(Response.Status.ACCEPTED).entity(token).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
