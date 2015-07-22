package com.hollonconsulting.solarwars.server.gamerules;

import com.hollonconsulting.solarwars.server.generator.ServerGenerator;
import com.hollonconsulting.solarwars.server.repository.StarRepository;
import com.hollonconsulting.solarwars.server.service.StarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupHousekeeper implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StartupHousekeeper.class);

    private StarService starService;
    private ServerGenerator serverGenerator;

    @Autowired
    public void setStarService(StarService starService){
        this.starService = starService;
    }

    @Autowired
    public void setServerGenerator(ServerGenerator serverGenerator){
        this.serverGenerator = serverGenerator;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        LOGGER.info("Context refreshed event.");

        Thread generationThread = new Thread(serverGenerator);
        generationThread.start();
    }
}
