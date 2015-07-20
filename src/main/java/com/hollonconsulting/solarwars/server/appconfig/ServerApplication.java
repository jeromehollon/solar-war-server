package com.hollonconsulting.solarwars.server.appconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.hollonconsulting.solarwars.server"})
public class ServerApplication {
    public static void main(String[] args){
        SpringApplication.run(ServerApplication.class);
    }
}