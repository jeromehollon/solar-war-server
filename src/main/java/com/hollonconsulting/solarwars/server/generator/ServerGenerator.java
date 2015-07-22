package com.hollonconsulting.solarwars.server.generator;

import com.hollonconsulting.solarwars.server.service.StarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerGenerator implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerGenerator.class);


    private StarService starService;
    private ServerGenerator serverGenerator;

    @Autowired
    public void setStarService(StarService starService){
        this.starService = starService;
    }

    public void run() {
        LOGGER.info("Launching generation.");

        cleanUpOld();
    }

    private void cleanUpOld() {
        starService.deleteAll();
    }
}
