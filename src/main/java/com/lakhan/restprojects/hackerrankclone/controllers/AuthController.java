package com.lakhan.restprojects.hackerrankclone.controllers;

import com.lakhan.restprojects.hackerrankclone.dtos.*;
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
        log.info("Login Request for user {} ", loginRequest.getEmail());
        return authService.login(loginRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout (@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        authService.logout(refreshTokenRequest);
        return ResponseEntity.ok().body("{\"message\" : \"Refresh Token Deleted\"}");
    }

    @GetMapping("/account-verification/{verificationToken}")
    public ResponseEntity<String> verifyAccount (@PathVariable("verificationToken") String verificationToken) {
        log.info("Validating token {} ", verificationToken);
        authService.verifyAccount(verificationToken);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }

    @GetMapping("/account-verification/send-verification-email")
    public ResponseEntity<String> sendVerificationEmail(@RequestParam("email") String email) {
        authService.sendVerificationEmail(email);
        return new ResponseEntity<>("{\"message\" : \"Tried resending verification email\"}", OK);
    }

    @PostMapping("/refresh")
    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }
}
