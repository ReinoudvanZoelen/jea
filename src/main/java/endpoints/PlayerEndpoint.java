package endpoints;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import services.PlayerService;
import javax.ws.rs.core.Response;

import models.Player;


@Path("/player")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerEndpoint {

    @Inject
    PlayerService service;

    @GET
    @Path("")
    public String getSlash() {
        return "{\"result\":\"" + "it works!" + "\"}";
    }

    @GET
    @Path("/{id}")
    public Response getMockById(@PathParam("id") int id){
        Player player = new Player("Reinoud van Zoelen", "reinoudvanzoelen@gmail.com", "myfakepassword", "ADMIN");
        return Response.ok().entity(player).build();
    }
}


