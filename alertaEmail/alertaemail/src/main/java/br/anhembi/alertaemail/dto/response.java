package br.anhembi.alertaemail.dto;

public class response {
    private Long notifId;
    private String mensagem;
    private boolean suspeita;
    private Long userId;
    private String email;


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
    public boolean isSuspeita() {
        return suspeita;
    }
    
    public response() {
    }
    public response(String mensagem, Long notifId, Boolean suspeita, Long userId) {
        this.notifId = notifId;
        this.mensagem = mensagem;
        this.suspeita = suspeita;
        this.userId = userId;
    }
    public boolean setSuspeita(boolean suspeita) {
        return this.suspeita = suspeita;
    }
    public String getEmail() {
        return email;
    }
    public String setEmail(String email) {
        return this.email = email;
    }
    public response(Long notifId, String mensagem, boolean suspeita, Long userId, String email) {
        this.notifId = notifId;
        this.mensagem = mensagem;
        this.suspeita = suspeita;
        this.userId = userId;
        this.email = email;
    }


}
