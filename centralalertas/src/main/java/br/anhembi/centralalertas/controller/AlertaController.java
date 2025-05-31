package br.anhembi.centralalertas.controller;

import br.anhembi.centralalertas.dto.AlertaDTO;
import br.anhembi.centralalertas.service.Alertaservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alertas")
public class AlertaController {
    public void processarAlerta(AlertaDTO alerta) {
        
    }

    @Autowired
    private AlertaService alertaService;

    @PostMapping
    public ResponseEntity<String> receberAlerta(@RequestBody AlertaDTO alerta) {
        alertaService.processarAlerta(alerta);
        return ResponseEntity.ok("Alerta processado com sucesso.");
    }
}