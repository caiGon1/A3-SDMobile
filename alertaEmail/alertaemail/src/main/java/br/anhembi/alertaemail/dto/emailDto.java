package br.anhembi.alertaemail.dto;

public class emailDto {
    private boolean suspeita;
    private Long userId;
    private Long alertaId;
    private Long notifId;
    private String email;
    private String mensagem;
    public boolean isSuspeita() {
        return suspeita;
    }
    public boolean setSuspeita(boolean suspeita) {
        return this.suspeita = suspeita;
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
    public Long getNotifId() {
        return notifId;
    }
    public Long setNotifId(Long notifId) {
        return this.notifId = notifId;
    }
    public emailDto() {
    }
    public emailDto(boolean suspeita, Long userId, Long alertaId, Long notifId, String email, String mensagem) {
        this.suspeita = suspeita;
        this.userId = userId;
        this.alertaId = alertaId;
        this.notifId = notifId;
        this.email = email;
        this.mensagem = mensagem;
    }
    

    public emailDto(boolean suspeita, Long notifId, String mensagem) {
        this.suspeita = suspeita;
        this.notifId = notifId;
        this.mensagem = mensagem;
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    // Getters and Setters

    
}
