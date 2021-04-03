package com.lakhan.restprojects.hackerrankclone.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static io.jsonwebtoken.Jwts.parser;
import static java.util.Date.from;

@Service
public class JwtProvider {
    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(from(Instant.now()))
                .setExpiration(from(Instant.now().plusSeconds(10)))
//                .signWith(new Key())
                .compact();
    }

    public String generateTokenWithUserName(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(from(Instant.now()))
    //            .signWith(getPrivateKey())
                //.setExpiration(from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .setExpiration(from(Instant.now().plusSeconds(10)))
                .compact();
    }

    public boolean validateToken(String jwt) {
       //parser().setSigningKey(new PublicKey()).parseClaimJws(jwt);
        parser().parseClaimsJwt(jwt);
        return true;
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = parser()
//                .setSigningKey(getPublickey())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
