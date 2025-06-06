package br.anhembi.barramento.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import br.anhembi.barramento.dto.requisicao;
import br.anhembi.barramento.dto.response;
import br.anhembi.barramento.model.model;
import br.anhembi.barramento.repo.repository;

@Component
public class NotificacaoRequestConsumer {

    response resposta = new response();
    private final KafkaTemplate<String, response> kafkaTemplate;
    private final repository repository;

    public NotificacaoRequestConsumer(KafkaTemplate<String, response> kafkaTemplate,
                                      repository repository) {
        this.kafkaTemplate = kafkaTemplate;
        this.repository = repository;
    }
 
    @KafkaListener(topics = "notificacao.request", groupId = "grupo-barramento")
    public void consumir(requisicao dto) {
        System.out.println(dto.getUserId()+ dto.getMensagem());
        Long notif = repository.findFirstByUserIdAndMensagemOrderByNotifIdDesc(dto.getUserId(), dto.getMensagem())
                .map(model::getNotifId)
                .orElse(null);
        boolean suspeita = repository.existsByNotifIdAndMensagem(notif, dto.getMensagem());
        Long userId = dto.getUserId();
        String email = dto.getEmail();

        System.out.println("Suspeita: "+ suspeita+ "Notif Id: " +notif);
        kafkaTemplate.send("notificacao.response", new response(notif, suspeita, email, userId));
    }

}

