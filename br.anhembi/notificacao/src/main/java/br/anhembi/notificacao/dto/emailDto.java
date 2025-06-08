package br.anhembi.notificacao.dto;

public class emailDto {
    private boolean suspeita;
    private Long userId;
    private String email;
    private Long notifId;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Long getNotifId() {
        return notifId;
    }

    public void setNotifId(Long notifId) {
        this.notifId = notifId;
    }
    public emailDto() {
    }
    public emailDto(boolean suspeita, Long userId, String email, Long notifId) {
        this.suspeita = suspeita;
        this.userId = userId;
        this.email = email;
        this.notifId = notifId;
    }

}
