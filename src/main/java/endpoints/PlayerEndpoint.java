package endpoints;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import datalayer.IPlayerDal;
import entities.Player;

@Path("/players")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerEndpoint {

    @Inject
    IPlayerDal service;

    // Hateoas
    @Context
    UriInfo uriInfo;

    @GET
    @Path("/id/{id}")
    public Response getById(@PathParam("id") int id) {
        Player player = service.getById(id);
        if (player == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        player.setLink(Link.fromUri(uriInfo.getAbsolutePath()).rel("self").type("GET").build());

        return Response.ok().entity(player).build();
    }

    @GET
    public Response getAll() {
        List<Player> players = service.getAll();
        for (Player player : players) {
            player.setLink(Link.fromUri(uriInfo.getAbsolutePath()).rel("self").type("GET").build());
        }
        return Response.ok().entity(players).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Player player) {
        service.update(player);
        return Response.ok().build();
    }

    @POST
    @Path("/update")
    public Response update(Player player) {
        service.update(player);
        return Response.ok().build();
    }
}