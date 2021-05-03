package com.lakhan.restprojects.hackerrankclone.controllers;

import com.lakhan.restprojects.hackerrankclone.dtos.AuthenticationResponse;
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
        return new ResponseEntity<>("{\"message\": \"User Registration Successful\"}", OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        log.info("Login Request for user " + loginRequest.getEmail());
        return authService.login(loginRequest);
    }

    @GetMapping("/account-verification/{verificationToken}")
    public ResponseEntity<String> verifyAccount (@PathVariable("verificationToken") String verificationToken) {
        log.info("Validating token {} ", verificationToken);
        authService.verifyAccount(verificationToken);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout() {
        return new ResponseEntity<>("Logout Successful", OK);
    }
}
