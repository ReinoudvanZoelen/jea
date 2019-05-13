package endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import interceptors.JWTTokenNeeded;

@Path("/authenticationrequired")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationRequiredEndpoint {

    @GET
    @Path("/user")
    @JWTTokenNeeded
    public Response user(@HeaderParam("Authorization") String authorizaionString) {
        return Response.ok().entity(authorizaionString).build();
    }

    @GET
    @Path("/admin")
    public Response admin() {
        return Response.ok().build();
    }
}