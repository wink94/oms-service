package com.windula.oms.config;


import static com.windula.oms.common.Constants.SystemStatus.ACTIVE;
import static com.windula.oms.common.Constants.SystemStatus.INACTIVE;

public class SystemStatusProvider {

    private static String systemStatus = INACTIVE;

    private SystemStatusProvider() {

    }

    public static void setSystemStatus(String status) {
        systemStatus = status;
    }

    public static boolean isSystemActive() {
        return systemStatus.equals(ACTIVE);
    }
}
