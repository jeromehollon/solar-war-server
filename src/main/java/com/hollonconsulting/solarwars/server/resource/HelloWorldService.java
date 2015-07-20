package com.hollonconsulting.solarwars.server.resource;

import org.springframework.stereotype.Component;
import javax.ws.rs.Path;
import javax.ws.rs.GET;

@Component
@Path("/")
public class HelloWorldService {

    @GET
    @Path("hello")
    public String getHelloWorld(){
        return "It's alive!";
    }
}
