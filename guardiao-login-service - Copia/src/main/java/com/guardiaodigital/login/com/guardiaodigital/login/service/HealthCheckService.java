package com.guardiaodigital.login.com.guardiaodigital.login.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;

@Service
public class HealthCheckService {

    private final static RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 5000) // Check every 5 seconds
    public static boolean isServiceUp() {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/notificacao/health", String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}

