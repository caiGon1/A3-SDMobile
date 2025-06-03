package br.anhembi.centralalertas.dto;

import java.time.LocalDate;
import org.springframework.http.HttpEntity;
import java.time.LocalDate;
import config.*;


public class EventoDTO {
    private Long userId;
    private Long notifId;
    private String mensagem;
    private LocalDate data;

    EventoDTO evento = new EventoDTO();

evento.setUserId ((long) 1);
evento.setNotifId(repo.findTopByUserIdOrderByNotifIdDesc((long) 1).map(model::getNotifId).orElse (other: null));
evento.setData(repo.findTopByUserIdOrderByNotifIdDesc((long) 1).map(model::getData).orElse(other: null));
evento.setMensagem (mensagem);

}
