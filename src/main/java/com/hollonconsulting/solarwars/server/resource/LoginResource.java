package com.hollonconsulting.solarwars.server.resource;


import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Component
@Path("/")
public class LoginResource {

    @POST
    @Path("login")
    @Produces("application/json")
    @Consumes("application/json")
    public Response postLogin(){
        return Response.ok().build();
    }
}
