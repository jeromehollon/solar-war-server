package com.hollonconsulting.solarwars.server.appconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories("com.hollonconsulting.solarwars.server.repository")
@ComponentScan({"com.hollonconsulting.solarwars.server"})
@EntityScan("com.hollonconsulting.solarwars.server.entity")
public class ServerApplication extends SpringBootServletInitializer  {
    public static void main(String[] args){
        SpringApplication.run(ServerApplication.class);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application
                .showBanner(true)
                .sources(ServerApplication.class);
    }
}