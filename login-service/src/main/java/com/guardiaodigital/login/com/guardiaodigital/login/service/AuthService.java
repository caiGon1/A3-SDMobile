package com.guardiaodigital.login.com.guardiaodigital.login.service;

import com.guardiaodigital.login.com.guardiaodigital.login.dto.LoginRequest;
import com.guardiaodigital.login.com.guardiaodigital.login.dto.dtoVerif;
import com.guardiaodigital.login.com.guardiaodigital.login.model.User;
import com.guardiaodigital.login.com.guardiaodigital.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class AuthService {
    String url = "http://localhost:8080/notificacao";
    private final RestTemplate restTemplate;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> authenticate(LoginRequest request) {
        dtoVerif dtoV = new dtoVerif();
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            dtoV.setLogin(true);
            dtoV.setUserId(user.get().getUserId());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<dtoVerif> httpEntity = new HttpEntity<>(dtoV, headers);
        
        try {
            restTemplate.postForEntity(url, httpEntity, dtoVerif.class);
            System.out.println("Login enviado");
        } catch (Exception e) {
            System.err.println("Erro ao enviar: " + e.getMessage());
        }
            return ResponseEntity.ok("Login realizado com sucesso.");
            
        }
        dtoV.setLogin(false);
        dtoV.setUserId(user.get().getUserId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<dtoVerif> httpEntity = new HttpEntity<>(dtoV, headers);
        
        try {
            restTemplate.postForEntity(url, httpEntity, dtoVerif.class);
            System.out.println("Login enviado");
        } catch (Exception e) {
            System.err.println("Erro ao enviar: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
    }
}