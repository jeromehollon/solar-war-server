package com.hollonconsulting.solarwars.server.appconfig;

import com.hollonconsulting.solarwars.server.appconfig.exception.AppExceptionMapper;
import com.hollonconsulting.solarwars.server.appconfig.exception.WebApplicationExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;
import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/")
public class RestResourceConfig extends ResourceConfig {
    public RestResourceConfig() {
        register(AppExceptionMapper.class);
        register(WebApplicationExceptionMapper.class);
        packages("com.hollonconsulting.solarwars.server.resource");
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }
}
