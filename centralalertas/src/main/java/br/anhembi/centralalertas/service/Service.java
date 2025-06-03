package br.anhembi.centralalertas.service;
import org.springframework.web.client.RestTemplate;
import config.*;


public class Service {
private Service service = new Service();


public void service (RestTemplate restTemplate) {

this.restTemplate = restTemplate;

}

}