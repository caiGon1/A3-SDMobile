package br.anhembi.centralalertas.service;

import br.anhembi.centralalertas.dto.AlertaDTO;
import br.anhembi.centralalertas.model.Usuario;
import br.anhembi.centralalertas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Alertaservice {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void processarAlerta(AlertaDTO alerta) {
        System.out.println("Alerta recebido: " + alerta.getMensagem());

        String mensagem = alerta.getMensagem();
        boolean suspeita = mensagem.toLowerCase().contains("pix") || mensagem.toLowerCase().contains("urgente");

        if (suspeita) {
            usuarioRepository.findById(alerta.getUserId()).ifPresent(usuario -> {
                String assunto = "Alerta de possível golpe";
                String corpo = "Mensagem suspeita recebida: \n\n" + mensagem;
                emailService.enviarEmail(usuario.getEmail(), assunto, corpo);
                System.out.println("E-mail enviado para: " + usuario.getEmail());
            });
        }

        if ("alta".equals(alerta.getSeveridade())) {
            System.out.println("ALTA SEVERIDADE – Acionando autoridades...");
        } else {
            System.out.println("Alerta de baixa/média severidade – Notificando família.");
        }
    }
}