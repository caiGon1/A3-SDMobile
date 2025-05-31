package br.anhembi.centralalertas.controller;

import org.springframework.beans.factory.annotation.Autowired;

import br.anhembi.centralalertas.dto.AlertaDTO;
import br.anhembi.centralalertas.service.EmailService;

public class AlertaService {

    @Autowired
private EmailService emailService;

    public void processarAlerta(AlertaDTO alerta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processarAlerta'");
    }


}
