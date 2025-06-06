package br.anhembi.barramento.dto;

public class requisicao {
    private Long userId;
    private String email;
    private String mensagem;

    

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

   


    public requisicao(Long userId, String email, String mensagem) {
        this.userId = userId;
        this.email = email;
        this.mensagem = mensagem;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
