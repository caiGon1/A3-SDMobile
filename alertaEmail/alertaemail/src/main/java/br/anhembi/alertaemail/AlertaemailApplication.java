package br.anhembi.alertaemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class AlertaemailApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlertaemailApplication.class, args);
	}

}
