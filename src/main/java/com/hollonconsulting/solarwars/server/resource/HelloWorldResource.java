package com.hollonconsulting.solarwars.server.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.ws.rs.Path;
import javax.ws.rs.GET;

@Component
@Path("/")
public class HelloWorldResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldResource.class);

    @GET
    @Path("hello")
    public String getHelloWorld(){
        return "It's alive!";
    }
}
