package com.guardiaodigital.login.com.guardiaodigital.login.dto;

import java.time.LocalDate;

public class eventodto {
    private Long userId;
    private String email;
    private LocalDate data;
    private String mensagem;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }   
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    
}
