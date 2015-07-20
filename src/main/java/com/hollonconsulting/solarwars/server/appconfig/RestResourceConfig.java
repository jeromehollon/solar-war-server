package com.hollonconsulting.solarwars.server.appconfig;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;
import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/")
public class RestResourceConfig extends ResourceConfig {
    public RestResourceConfig() {
        packages("com.hollonconsulting.solarwars.server.resource");
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }
}
