package br.anhembi.centralalertas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    @Autowired
    private RestTemplate restTemplate;

    public void chamarApiExterna(String url) {
        String resposta = restTemplate.getForObject(url, String.class);
        System.out.println("Resposta da API externa: " + resposta);
    }
}