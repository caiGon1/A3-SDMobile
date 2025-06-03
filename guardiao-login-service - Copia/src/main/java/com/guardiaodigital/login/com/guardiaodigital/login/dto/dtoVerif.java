package com.guardiaodigital.login.com.guardiaodigital.login.dto;

public class dtoVerif {
    private boolean login;
    private Long userId;
    
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
    
}
