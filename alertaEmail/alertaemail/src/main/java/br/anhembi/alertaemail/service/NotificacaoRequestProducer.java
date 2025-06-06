package br.anhembi.alertaemail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.anhembi.alertaemail.dto.emailDto;
import br.anhembi.alertaemail.dto.requisicao;
import br.anhembi.alertaemail.dto.response;


@Service
public class NotificacaoRequestProducer {

    emailDto email = new emailDto();

    @Autowired
    @Lazy
    private EmailService emailService;

    private final KafkaTemplate<String, requisicao> kafkaTemplate;

    public NotificacaoRequestProducer(KafkaTemplate<String, requisicao> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Long solicitarUltimaNotificacaoSuspeita(Long userId, String email) {
        requisicao dto = new requisicao();

        dto.setEmail(email);
        dto.setUserId(userId);
        dto.setMensagem("Notificação: Transação anormal (valor acima da média histórica)");
        kafkaTemplate.send("notificacao.request", dto); // envia para o tópico Kafka
        System.out.println("Enviando para Kafka: " + dto.getUserId() + " - " + dto.getMensagem()+ " - "+ dto.getEmail());

        return null;

    }


    @KafkaListener(topics = "notificacao.response", groupId = "grupo-barramento")
    public void consumirResposta(response email) {
         System.out.println("Objeto response recebido do Kafka: " + email.isSuspeita() + email.getNotifId());
        emailDto dto = new emailDto();
        dto.setEmail(email.getEmail());
        dto.setSuspeita(email.isSuspeita());
        dto.setUserId(email.getUserId());
        dto.setNotifId(email.getNotifId());
        emailService.processarVazio(email);

        

    }

    
}




