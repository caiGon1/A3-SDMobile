package br.anhembi.barramento.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.anhembi.barramento.dto.dto;
import br.anhembi.barramento.service.BarramentoService;

@RestController
@RequestMapping("/api/eventos")
public class controller {

private final BarramentoService service;

    public controller(BarramentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> receberEvento(@RequestBody dto dto) {
        service.processarEvento(dto);
        return ResponseEntity.ok("Evento recebido com sucesso");
    }

}
