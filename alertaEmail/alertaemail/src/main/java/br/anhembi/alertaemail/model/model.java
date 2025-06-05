package br.anhembi.alertaemail.model;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
@Table(name = "emails")
public class model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertaId;
    private Long userId;
    private Long notifId;
    private LocalDate data;
    private String email;
    private String assunto;
    private String mensagem;
    
    public Long getAlertaId() {
        return alertaId;
    }
    public void setAlertaId(Long alertaId) {
        this.alertaId = alertaId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getAssunto() {
        return assunto;
    }
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    public Long getNotifId() {
        return notifId;
    }
    public void setNotifId(Long notifId) {
        this.notifId = notifId;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
