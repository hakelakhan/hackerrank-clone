package com.lakhan.restprojects.hackerrankclone.controllers;

import com.lakhan.restprojects.hackerrankclone.dtos.LoginRequest;
import com.lakhan.restprojects.hackerrankclone.dtos.RegisterRequest;
import com.lakhan.restprojects.hackerrankclone.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@Slf4j
class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value="/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return new ResponseEntity<>("User Registration Successful", OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(LoginRequest loginRequest) {
        authService.login(loginRequest);
        return new ResponseEntity<>("Login Successful", OK);
    }

    @GetMapping("/account-verification/{verificationToken}")
    public ResponseEntity<String> verifyAccount (@PathVariable("verificationToken") String verificationToken) {
        log.info("Validating token {} ", verificationToken);
        authService.verifyAccount(verificationToken);
        return new ResponseEntity<String>("Account Activated Successfully", OK);
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout() {
        return new ResponseEntity<>("Logout Successful", OK);
    }
}
