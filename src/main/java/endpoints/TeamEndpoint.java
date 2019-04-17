package endpoints;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import datalayer.IPlayerDal;
import datalayer.ITeamDal;
import entities.Team;

@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
public class TeamEndpoint {

    @Inject
    ITeamDal service;

    @Inject
    IPlayerDal playerService;

    @GET
    @Path("/id/{id}")
    public Response getById(@PathParam("id") int id) {
        return Response.ok().entity(service.getById(id)).build();
    }

    @GET
    public Response getAll() {
        return Response.ok().entity(service.getAll()).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Team team) {
        service.add(team);
        return Response.ok().build();
    }

    @POST
    @Path("/update")
    public Response update(Team team){
        service.update(team);
        return Response.ok().build();
    }

    @POST
    @Path("/mock")
    public Response saveMock() {
        Team t = new Team();
        t.setName("Team Super Saiyan");
        t.setPlayers(playerService.getAll());
        this.service.add(t);

        return Response.ok().build();
    }
}