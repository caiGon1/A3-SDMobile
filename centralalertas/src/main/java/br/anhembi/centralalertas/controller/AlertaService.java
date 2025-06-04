package br.anhembi.centralalertas.controller;

import org.springframework.beans.factory.annotation.Autowired;

import br.anhembi.centralalertas.dto.AlertaDTO;
import br.anhembi.centralalertas.service.EmailService;

public class AlertaService {

    @Autowired
private EmailService emailService;
@Autowired


public void enviarAlertaParaUsuario(String email, String mensagem) {
    emailService.enviarEmail(email, "Alerta Importante", mensagem);
}

    public void processarAlerta(AlertaDTO alerta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processarAlerta'");
    }


}
