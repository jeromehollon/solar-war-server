package com.hollonconsulting.solarwars.server.resource.map;

import com.hollonconsulting.solarwars.server.appconfig.ApplicationContextHolder;
import com.hollonconsulting.solarwars.server.model.response.map.MapResponse;
import com.hollonconsulting.solarwars.server.service.PlanetService;
import com.hollonconsulting.solarwars.server.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Component
@Path("map/")
public class MapResource {
    private PlanetService planetService;
    private StarService starService;

    /*@Autowired
    public void setPlanetService(PlanetService planetService) { this.planetService = planetService;}

    @Autowired
    public void setStarService(StarService starService) { this.starService = starService;}*/

    //Something is wrong with Jersey's interuption of Autowire
    //http://stackoverflow.com/questions/24399528/jersey-singleton-using-spring-method-cannot-be-cast-to-constructor
    //https://java.net/jira/browse/JERSEY-2681
    {
        this.planetService = ApplicationContextHolder.getContext().getBean(PlanetService.class);
        this.starService = ApplicationContextHolder.getContext().getBean(StarService.class);
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public Response getMap(){
        MapResponse response = new MapResponse(planetService.findAll(), starService.findAll());
        return Response.ok(response).build();
    }
}
