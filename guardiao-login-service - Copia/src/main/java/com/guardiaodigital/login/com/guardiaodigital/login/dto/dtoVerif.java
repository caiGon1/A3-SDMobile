package com.guardiaodigital.login.com.guardiaodigital.login.dto;

public class dtoVerif {
    private boolean login;
    private Long userId;
    private String email;

    
    
    public boolean isLogin() {
        return login;
    }
    public void setLogin(boolean login) {
        this.login = login;
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
