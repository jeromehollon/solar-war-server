package com.hollonconsulting.solarwars.server.gamerules;

import com.hollonconsulting.solarwars.server.appconfig.ApplicationContextHolder;
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        LOGGER.info("Context refreshed event.");

        ServerGenerator generator = ApplicationContextHolder.getContext().getBean(ServerGenerator.class);

        Thread generationThread = new Thread(generator);
        generationThread.start();
    }
}
