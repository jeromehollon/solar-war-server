package com.hollonconsulting.solarwars.server.resource.api.star;

import com.hollonconsulting.solarwars.server.entity.Planet;
import com.hollonconsulting.solarwars.server.entity.Player;
import com.hollonconsulting.solarwars.server.entity.Star;
import com.hollonconsulting.solarwars.server.model.response.planet.PlanetResponse;
import com.hollonconsulting.solarwars.server.service.PlanetService;
import com.hollonconsulting.solarwars.server.service.PlayerService;
import com.hollonconsulting.solarwars.server.service.StarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

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
        PlanetResponse planetResponse;

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();

        Player currentPlayer = playerService.findByUsername(username);
        Star currentStar = starService.findById(starId);

        if(currentStar == null){
            throw new NotFoundException("Could not find star with an id of " + starId);
        }

        Player owner = (currentStar.getPlayerId() == 0) ? null : playerService.findById(currentStar.getPlayerId());
        Star star = starService.findById(currentStar.getStarId());

        boolean doScrub = (currentStar.getPlayerId() == 0 || owner.getId() != currentPlayer.getId());

        LOGGER.debug("Building Response.");
        planetResponse = new PlanetResponse(currentStar, owner, star, doScrub);
        LOGGER.debug("Sending Response. {}", planetResponse);

        return Response.ok(planetResponse).build();
    }
}
