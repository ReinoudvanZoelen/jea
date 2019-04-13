package endpoints;

import entities.Player;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("/mock/players")
public class PlayerMockEndpoint {

    private List<Player> getMockPlayers() {
        List<Player> players = new ArrayList<Player>();
        players.add(new Player("Reinoud van Zoelen", "Mock@gmail.com", "myfakepassword12345"));
        players.add(new Player("Bono IJpelaar", "Mock@gmail.com", "myfakepassword12345"));
        players.add(new Player("Niels Werkman", "Mock@gmail.com", "myfakepassword12345"));
        return players;
    }

    @GET
    public Response getAll() {
        return Response.ok().entity(getMockPlayers()).build();
    }

    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Player player) {
        List<Player> players = getMockPlayers();
        players.add(player);
        return Response.ok().entity(players).build();
    }
}
