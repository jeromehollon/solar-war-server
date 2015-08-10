package com.hollonconsulting.solarwars.server.generator;

import com.hollonconsulting.solarwars.server.appconfig.Defaults;
import com.hollonconsulting.solarwars.server.entity.Planet;
import com.hollonconsulting.solarwars.server.entity.Star;
import com.hollonconsulting.solarwars.server.generator.util.MarkovNameGenerator;
import com.hollonconsulting.solarwars.server.service.PlanetService;
import com.hollonconsulting.solarwars.server.service.StarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class ServerGenerator implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerGenerator.class);


    private StarService starService;
    private PlanetService planetService;

    @Autowired
    public void setStarService(StarService starService){
        this.starService = starService;
    }
    @Autowired
    public void setPlanetService(PlanetService planetService){
        this.planetService = planetService;
    }

    public void run() {
        LOGGER.info("Launching generation.");

        LOGGER.info("Cleaning up...");
        cleanUpOld(false);
        LOGGER.info("Generating...");
        setupNew();
    }

    private void cleanUpOld(boolean fullCleanup) {
        if(fullCleanup){
            starService.deleteAll();
            planetService.deleteAll();
        }
        //clear out server logs and sessions
    }


    private void setupNew() {
        if(starService.countAll() == 0) {
            LOGGER.info("Generating...Stars");
            StarGenerator starGenerator = new StarGenerator(Defaults.NUMBER_OF_STARS);
            Star[] stars = starGenerator.generate();

            for (Star star : stars) {
                starService.create(star);
            }
        }else{
            LOGGER.info("Generating...Stars...skipping.");
        }

        if(planetService.countAll() == 0){
            LOGGER.info("Generating...Worlds");
            PlanetGenerator planetGenerator = new PlanetGenerator(starService.findAll());
            Planet[] planets = planetGenerator.generate();
            for(Planet planet : planets){
                planetService.create(planet);
            }
        }else{
            LOGGER.info("Generating...Worlds...skipping");
        }

        LOGGER.info("Generating...Fleets");
        LOGGER.info("Done.");
        LOGGER.info("Server Available.");
    }
}
