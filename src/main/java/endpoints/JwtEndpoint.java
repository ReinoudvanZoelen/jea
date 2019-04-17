package endpoints;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/jwt")
@Produces(MediaType.APPLICATION_JSON)
public class JwtEndpoint {

    @GET
    @Path("/user")
    public Response user() {
        return Response.ok().build();
    }
    
    @GET
    @Path("/admin")
    public Response admin() {
        return Response.ok().build();
    }
}