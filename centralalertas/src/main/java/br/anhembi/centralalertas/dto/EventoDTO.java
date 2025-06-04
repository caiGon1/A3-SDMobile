package br.anhembi.centralalertas.dto;

import java.time.LocalDate;

public class EventoDTO {

    private Long userID;
    private Long alertaID;
    private String mensagem;
    private LocalDate data;

    // Construtor padrão
    public EventoDTO() {}

    // Getters e Setters
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getAlertaID() {
        return alertaID;
    }

    public void setAlertaID(Long alertaID) {
        this.alertaID = alertaID;
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
}