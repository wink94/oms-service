package com.windula.oms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import static com.windula.oms.common.Constants.EnvironmentValue.AVAILABLE_ENVIRONMENTS;
import static com.windula.oms.common.Constants.EnvironmentValue.SERVER_ENVIRONMENT_VARIABLE;

@SpringBootApplication
public class OmsApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OmsApplication.class);


	public static void main(String[] args) {
		LOGGER.info("Application is starting...");

		setEnvironment();

		SpringApplication.run(OmsApplication.class, args);
	}

	private static void setEnvironment() {
		String env = System.getenv(SERVER_ENVIRONMENT_VARIABLE);

		if (env == null) {
			LOGGER.error("Usage: please specify the environment variable");
			System.exit(-1);
		} else if (!AVAILABLE_ENVIRONMENTS.contains(env)) {
			LOGGER.error("Did not find a valid value for SERVER_ENVIRONMENT_VARIABLE. Acceptable values are for SERVER_ENVIRONMENT_VARIABLE are ");
			for (String s : AVAILABLE_ENVIRONMENTS) {
				LOGGER.error(s);
			}
			System.exit(-1);
		}
	}

	@Bean
	public ServletWebServerFactory servletContainer() {
		return new TomcatServletWebServerFactory();
	}
}
