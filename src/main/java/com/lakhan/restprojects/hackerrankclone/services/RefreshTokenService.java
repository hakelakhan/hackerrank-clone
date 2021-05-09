package com.lakhan.restprojects.hackerrankclone.services;

import com.lakhan.restprojects.hackerrankclone.daos.RefreshTokenRepository;
import com.lakhan.restprojects.hackerrankclone.exception.CodieException;
import com.lakhan.restprojects.hackerrankclone.models.RefreshToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
        return refreshTokenRepository.saveAndFlush(refreshToken);
    }
    public void validateToken(String token) {
        refreshTokenRepository.findByToken(token).orElseThrow(() -> new CodieException("Invalid Refresh Token"));
    }

    public void deleteToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }

}
