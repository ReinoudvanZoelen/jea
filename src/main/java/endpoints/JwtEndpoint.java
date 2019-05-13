package endpoints;

import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import interceptors.VerifyJWTInterceptor;

@Path("/authenticationrequired")
@Produces(MediaType.APPLICATION_JSON)
public class JwtEndpoint {

    @GET
    @Path("/user")
    @Interceptors(VerifyJWTInterceptor.class)
    public Response user(@HeaderParam("token") String token) {
        return Response.ok().entity(token).build();
    }

    @GET
    @Path("/admin")
    public Response admin() {
        return Response.ok().build();
    }
}