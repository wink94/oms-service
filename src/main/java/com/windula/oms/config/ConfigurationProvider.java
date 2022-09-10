package com.windula.oms.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application-${SERVER_ENVIRONMENT_VARIABLE}.properties")
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class ConfigurationProvider {


}
