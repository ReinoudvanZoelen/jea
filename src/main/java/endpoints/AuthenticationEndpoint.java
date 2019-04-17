package endpoints;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import datalayer.IPlayerDal;
import entities.Player;
import models.Authentication;
import services.JwtService;

@Produces(MediaType.APPLICATION_JSON)
@Path("/auth")
public class AuthenticationEndpoint {

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
                return Response.ok((token)).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
