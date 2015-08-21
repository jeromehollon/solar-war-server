package com.hollonconsulting.solarwars.server.resource.api.star;

import com.hollonconsulting.solarwars.server.entity.Star;
import com.hollonconsulting.solarwars.server.model.response.star.StarResponse;
import com.hollonconsulting.solarwars.server.service.PlanetService;
import com.hollonconsulting.solarwars.server.service.PlayerService;
import com.hollonconsulting.solarwars.server.service.StarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Resource
@Path("api/star/")
public class StarResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(StarResource.class);

    @Autowired
    private PlayerService playerService;
    @Autowired
    private StarService starService;
    @Autowired
    private PlanetService planetService;


    @GET
    @Produces("application/json")
    @Path("{starId}")
    public Response getPlanet(@NotNull @PathParam("starId") Integer starId){
        Star star = starService.findById(starId);

        if(star == null){
            throw new NotFoundException("Could not find star with an id of " + starId);
        }

        List<String> planets = planetService.findNameByStar(starId);

        StarResponse response = new StarResponse(star, planets);

        return Response.ok(response).build();
    }
}
