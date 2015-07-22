package com.hollonconsulting.solarwars.server.appconfig;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;


@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories("com.hollonconsulting.solarwars.server.repository")
@EntityScan("com.hollonconsulting.solarwars.server.entity")
@ComponentScan({"com.hollonconsulting.solarwars.server"})
public class SolarWarServerDbConfig {

    @Bean(name = "solarWarDataSource")
    @Primary
    @ConfigurationProperties(prefix = "datasource.solarWarServerDb")
    public DataSource solarWarDataSource() {
        return DataSourceBuilder.create().build();
    }
}
