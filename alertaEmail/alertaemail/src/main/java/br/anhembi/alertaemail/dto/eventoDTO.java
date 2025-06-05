package br.anhembi.alertaemail.dto;

import java.time.LocalDate;

public class eventoDTO {
    private Long userId;
    private Long alertaId;
    private Long notifId;
    private String email;
    private String mensagem;
    private LocalDate data;

    public Long getNotifId() {
        return notifId;
    }

    public void setNotifId(Long notifId) {
        this.notifId = notifId;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getAlertaId() {
        return alertaId;
    }
    public void setAlertaId(Long alertaId) {
        this.alertaId = alertaId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
