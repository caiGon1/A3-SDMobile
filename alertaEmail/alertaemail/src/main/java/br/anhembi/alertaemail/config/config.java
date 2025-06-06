package br.anhembi.alertaemail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.client.RestTemplate;

@EnableKafka
@Configuration
public class config {
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    
}
