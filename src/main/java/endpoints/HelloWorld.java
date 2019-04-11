package endpoints;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import services.HelloService;

@Path("/helloworld")
public class HelloWorld {

    @Inject
    HelloService helloService;

    @GET
    @Path("/json")
    @Produces({ "application/json" })
    public String getHelloWorldJSON() {
        return "{\"result\":\"" + helloService.createHelloMessage("World") + "\"}";
    }

    @GET
    @Path("/xml")
    @Produces({ "application/xml" })
    public String getHelloWorldXML() {
        return "<xml><result>" + helloService.createHelloMessage("World") + "</result></xml>";
    }

}
