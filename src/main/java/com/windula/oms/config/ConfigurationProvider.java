package com.windula.oms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("classpath:application-${SERVER_ENVIRONMENT_VARIABLE}.properties")
public class ConfigurationProvider {

    @Bean
    public ConfigurationProvider ConfigurationProvider() {
        return this;
    }

    @Value("${host}")
    private String host;
    @Value("${database}")
    private String database;
    @Value("${user}")
    private String user;
    @Value("${password}")
    private String password;


    public String getHost() {
        return host;
    }

    public String getDatabase() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

// TODO : Fetch DB credential using AWS SSM is possible
}
