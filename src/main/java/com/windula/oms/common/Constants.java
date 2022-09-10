package com.windula.oms.common;

import java.util.List;

public class Constants {

    public static class EnvironmentValue {
        public static final String SERVER_ENVIRONMENT_VARIABLE = "SERVER_ENVIRONMENT_VARIABLE";
        public static final String DEVELOPMENT = "DEV";

        public static final List<String> AVAILABLE_ENVIRONMENTS = List.of(DEVELOPMENT);

        private EnvironmentValue() {

        }
    }
}
