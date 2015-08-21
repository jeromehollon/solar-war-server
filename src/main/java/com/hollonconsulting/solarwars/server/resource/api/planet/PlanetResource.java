package com.hollonconsulting.solarwars.server.resource.api.planet;

import com.hollonconsulting.solarwars.server.entity.Planet;
import com.hollonconsulting.solarwars.server.entity.Player;
import com.hollonconsulting.solarwars.server.entity.Star;
import com.hollonconsulting.solarwars.server.model.response.planet.PlanetResponse;
import com.hollonconsulting.solarwars.server.resource.api.player.PlayerResource;
import com.hollonconsulting.solarwars.server.service.PlanetService;
import com.hollonconsulting.solarwars.server.service.PlayerService;
import com.hollonconsulting.solarwars.server.service.StarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.NotFoundException;

@Component
@Path("api/planet/")
public class PlanetResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlanetResource.class);

    @Autowired
    private PlayerService playerService;
    @Autowired
    private StarService starService;
    @Autowired
    private PlanetService planetService;


    @GET
    @Produces("application/json")
    @Path("{planetId}")
    public Response getPlanet(@NotNull @PathParam("planetId") Integer planetId){
        PlanetResponse planetResponse;

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();

        Player currentPlayer = playerService.findByUsername(username);
        Planet currentPlanet = planetService.findById(planetId);

        if(currentPlanet == null){
            throw new NotFoundException("Could not find planet with an id of " + planetId);
        }

        Player owner = (currentPlanet.getPlayerId() == 0) ? null : playerService.findById(currentPlanet.getPlayerId());
        Star star = starService.findById(currentPlanet.getStarId());

        boolean doScrub = (currentPlanet.getPlayerId() == 0 || owner.getId() != currentPlayer.getId());

        planetResponse = new PlanetResponse(currentPlanet, owner, star, doScrub);

        return Response.ok(planetResponse).build();
    }
}
