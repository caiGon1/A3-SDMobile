package br.anhembi.alertaemail.dto;

public class requisicao {
    private Long userId;
    private String mensagem;
    private String email;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    public requisicao() {
    }
    public requisicao(Long userId, String mensagem, String email) {
        this.userId = userId;
        this.mensagem = mensagem;
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    

}
