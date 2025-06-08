package br.anhembi.notificacao.dto;

import java.time.LocalDate;


public class EventoDTO {
    private Long userId;
    private Long notifId;
    private String mensagem;
    private LocalDate data;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNotifId() {
        return notifId;
    }

    public void setNotifId(Long notifId) {
        this.notifId = notifId;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public EventoDTO() {
    }

    public EventoDTO(Long userId, Long notifId, String mensagem, LocalDate data) {
        this.userId = userId;
        this.notifId = notifId;
        this.mensagem = mensagem;
        this.data = data;
    }
    
}