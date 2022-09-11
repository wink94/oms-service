package com.windula.oms.common;

import java.util.List;

public class Constants {

    public static final String VERSION = "1.0.0";
    public static final String CORRELATION_ID_LOG_VAR_NAME = "correlationId";
    public static final String HEADER_KEY_CORRELATION_ID = "X-Syy-Correlation-Id";

    public static class SystemStatus {
        public static final String ACTIVE = "Active";
        public static final String INACTIVE = "Inactive";

        private SystemStatus() {
        }
    }

    public static class EnvironmentValue {
        public static final String SERVER_ENVIRONMENT_VARIABLE = "SERVER_ENVIRONMENT_VARIABLE";
        public static final String DEVELOPMENT = "DEV";

        public static final List<String> AVAILABLE_ENVIRONMENTS = List.of(DEVELOPMENT);

        private EnvironmentValue() {

        }
    }

    public static class CorsFilterConst {
        public static final String HEADER_NAME_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
        public static final String HEADER_NAME_ALLOW_METHODS = "Access-Control-Allow-Methods";
        public static final String HEADER_NAME_MAX_AGE = "Access-Control-Max-Age";
        public static final String HEADER_NAME_ALLOW_HEADERS = "Access-Control-Allow-Headers";
        public static final String VALUE_ALLOW_ORIGIN = "*";
        public static final String VALUE_ALLOW_METHODS = "POST, GET, OPTIONS, DELETE";
        public static final String VALUE_MAX_AGE = "3600";
        public static final String VALUE_ALLOW_HEADERS = "origin, content-type, accept, x-requested-with, clientid, traceFlag";

        private CorsFilterConst() {

        }
    }
}
