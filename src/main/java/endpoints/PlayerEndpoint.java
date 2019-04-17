package endpoints;

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

    @GET
    @Path("/id/{id}")
    public Response getById(@PathParam("id") int id) {
        Player player = service.getById(id);
        if (player == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok().entity(player).build();
    }

    @GET
    public Response getAll() {
        return Response.ok().entity(service.getAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Player player) {
        service.add(player);
        return Response.ok().build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Player player) {
        service.add(player);
        return Response.ok().build();
    }

    @POST
    @Path("/update")
    public Response update(Player player){
        service.update(player);
        return Response.ok().build();
    }
}