package br.anhembi.alertaemail.controller;

import org.springframework.web.bind.annotation.RestController;
import br.anhembi.alertaemail.dto.emailDto;
import br.anhembi.alertaemail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@RestController
@RequestMapping("/alertaemail")


public class controller {

    @Autowired
    private EmailService emailService;



    @PostMapping
    public ResponseEntity<String> verificarSuspeita(@RequestBody emailDto dto) {
        emailService.processarDto(dto);
    return ResponseEntity.ok("Recebido");
    }
}
