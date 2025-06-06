package com.guardiaodigital.login.com.guardiaodigital.login.service;

import com.guardiaodigital.login.com.guardiaodigital.login.dto.LoginRequest;
import com.guardiaodigital.login.com.guardiaodigital.login.dto.dtoVerif;
import com.guardiaodigital.login.com.guardiaodigital.login.model.User;
import com.guardiaodigital.login.com.guardiaodigital.login.repository.UserRepository;
import com.guardiaodigital.login.com.guardiaodigital.login.dto.eventodto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class AuthService {
    static String email = "http://localhost:8081/alertaemail";
    static String url = "http://localhost:8080/notificacao";
    static String evento = "http://localhost:8082/api/eventos";
    private final RestTemplate restTemplate;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> authenticate(LoginRequest request) {
        dtoVerif dtoV = new dtoVerif();
        eventodto eventoDTO = new eventodto();

        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            dtoV.setLogin(true);
            dtoV.setEmail(user.get().getEmail());
            dtoV.setUserId(user.get().getUserId());

            eventoDTO.setMensagem("Login realizado com sucesso.");
            eventoDTO.setUserId(user.get().getUserId());
            eventoDTO.setData(java.time.LocalDate.now());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<dtoVerif> httpEntity = new HttpEntity<>(dtoV, headers);


    if (HealthCheckService.isServiceUp() == true) {
        try {
            restTemplate.postForEntity(url, httpEntity, dtoVerif.class); //envio a verificação
            System.out.println("Login realizado.");
        } catch (Exception e) {
            System.err.println("Erro ao enviar: " + e.getMessage());
        }
    } else {
        try {
            restTemplate.postForEntity(email, httpEntity, dtoVerif.class); //envio ao email.
            System.out.println("Enviado ao email.");
        } catch (Exception e) {
            System.err.println("Erro ao enviar: " + e.getMessage());
        }
    }

    HttpEntity<eventodto> httpEntityEvento = new HttpEntity<>(eventoDTO, headers); //envio ao barramento
    try {
        restTemplate.postForEntity(evento, httpEntityEvento, String.class);
        System.out.println("Evento enviado");
    } catch (Exception e) {
        System.err.println("Erro ao enviar evento: " + e.getMessage());
    }
    return ResponseEntity.ok("Login realizado.");

}



        dtoV.setLogin(false);
        dtoV.setUserId(user.get().getUserId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<dtoVerif> httpEntity = new HttpEntity<>(dtoV, headers);
        
        try {
            restTemplate.postForEntity(url, httpEntity, dtoVerif.class); //envio a verificação
            System.out.println("Login enviado");
        } catch (Exception e) {
            System.err.println("Erro ao enviar: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
    }
}