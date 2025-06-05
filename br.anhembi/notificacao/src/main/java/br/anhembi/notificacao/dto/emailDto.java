package br.anhembi.notificacao.dto;

public class emailDto {
    private boolean suspeita;
    private Long userId;
    private String destinatario;
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

}
