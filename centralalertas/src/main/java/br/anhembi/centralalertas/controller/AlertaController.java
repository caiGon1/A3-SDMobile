package br.anhembi.centralalertas.controller;

import br.anhembi.centralalertas.dto.AlertaDTO;
import br.anhembi.centralalertas.service.AlertaService;
import main.java.br.anhembi.centralalertas.controller.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.anhembi.centralalertas.service.EmailService;

@RestController
@RequestMapping("/alertas")
public class AlertaController {
    @Autowired
private EmailService emailService;

@PostMapping("/enviar-alerta")
public ResponseEntity<String> enviarAlerta(@RequestParam String email) {
    emailService.enviarEmail(email, "Alerta Importante", "Um novo alerta foi gerado!");
    return ResponseEntity.ok("E-mail enviado para " + email);
}

    @Autowired
    private AlertaService alertaService;

    @PostMapping
    public ResponseEntity<String> receberAlerta(@RequestBody AlertaDTO alerta) {
        alertaService.processarAlerta(alerta);
        return ResponseEntity.ok("Alerta processado com sucesso.");
    }
}