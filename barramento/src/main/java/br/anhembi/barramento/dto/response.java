package br.anhembi.barramento.dto;

public class response {
    String mensagem;
    String email;
    Long userId;
    Long notifId;
    Boolean suspeita;

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

    public response() {
    }

    public response(Long notifId, Boolean suspeita, String email, Long userId) {
        this.notifId = notifId;
        this.suspeita = suspeita;
        this.email = email;
        this.userId = userId;
    }
    public Boolean getSuspeita() {
        return suspeita;
    }   
    public void setSuspeita(Boolean suspeita) {
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
    
}