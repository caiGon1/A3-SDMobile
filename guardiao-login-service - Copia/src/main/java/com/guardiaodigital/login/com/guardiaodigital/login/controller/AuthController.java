package com.guardiaodigital.login.com.guardiaodigital.login.controller;

import com.guardiaodigital.login.com.guardiaodigital.login.dto.LoginRequest;
import com.guardiaodigital.login.com.guardiaodigital.login.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return authService.authenticate(request);
    }
}