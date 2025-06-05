package br.anhembi.alertaemail.dto;

public class emailDto {
    private boolean suspeita;
    private Long userId;
    private Long alertaId;
    private Long notifId;
    private String destinatario;
    public boolean isSuspeita() {
        return suspeita;
    }
    public void setSuspeita(boolean suspeita) {
        this.suspeita = suspeita;
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
    public String getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
    public Long getNotifId() {
        return notifId;
    }
    public void setNotifId(Long notifId) {
        this.notifId = notifId;
    }

    // Getters and Setters

    
}
