package com.windula.oms.controller;


import com.windula.oms.config.SystemStatusProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.windula.oms.common.Constants.VERSION;

@RestController
@RequestMapping({"/v1/oms/healthcheck","/oms/healthcheck", "/healthcheck"})
public class HealthController {

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getHealthCheck() {

        if (SystemStatusProvider.isSystemActive()) {
            String response = "Version: " + VERSION;
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
