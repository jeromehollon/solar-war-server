package com.hollonconsulting.solarwars.server.resource.api.player;


import com.hollonconsulting.solarwars.server.entity.Player;
import com.hollonconsulting.solarwars.server.entity.Roles;
import com.hollonconsulting.solarwars.server.model.request.RegistrationParams;
import com.hollonconsulting.solarwars.server.service.PlayerService;
import com.hollonconsulting.solarwars.server.service.RolesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Component
@Path("api/player/")
public class PlayerResource {
    private static Logger LOGGER = LoggerFactory.getLogger(PlayerResource.class);

    @Autowired
    private PlayerService playerService;
    @Autowired
    private RolesService rolesService;

    @POST
    @Path("register")
    @Produces("application/json")
    @Consumes("application/json")
    public Response register(@Valid @NotNull(message = "Must provide registration parameters.") RegistrationParams params){
        LOGGER.debug("Entering register function");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Player newPlayer = Player.CreateNewPlayer(params.getUsername(), passwordEncoder.encode(params.getPassword()), params.getEmail());

        LOGGER.debug("1");
        this.validateNewPlayer(newPlayer);
        LOGGER.debug("1.5");
        playerService.create(newPlayer);
        LOGGER.debug("2");

        LOGGER.debug("Created user: {}", newPlayer);

        Roles role = new Roles(Roles.Role.USER, newPlayer.getId());
        rolesService.create(role);

        LOGGER.debug("Created role: {}", role);

        LOGGER.debug("3");

        return Response.ok().build();
    }

    private void validateNewPlayer(Player newPlayer) {
        if(playerService.findByUsername(newPlayer.getUsername()) != null){
            LOGGER.debug("Throwing BadRequestException. Username already in use.");
            throw new BadRequestException("Username is already in use.");
        }
        if(!(newPlayer.getEmail() == null || newPlayer.getEmail().isEmpty()) && playerService.findByEmail(newPlayer.getEmail()) != null){
            LOGGER.debug("Throwing BadRequestException. Email already in use.");
            throw new BadRequestException("Email is already in use.");
        }
    }
}
