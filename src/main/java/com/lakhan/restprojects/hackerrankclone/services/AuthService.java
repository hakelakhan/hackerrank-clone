package com.lakhan.restprojects.hackerrankclone.services;

import com.lakhan.restprojects.hackerrankclone.daos.UsersRepository;
import com.lakhan.restprojects.hackerrankclone.daos.VerificationTokenRepository;
import com.lakhan.restprojects.hackerrankclone.dtos.AuthenticationResponse;
import com.lakhan.restprojects.hackerrankclone.dtos.LoginRequest;
import com.lakhan.restprojects.hackerrankclone.dtos.RegisterRequest;
import com.lakhan.restprojects.hackerrankclone.enums.RegistrationStatus;
import com.lakhan.restprojects.hackerrankclone.models.NotificationEmail;
import com.lakhan.restprojects.hackerrankclone.models.User;
import com.lakhan.restprojects.hackerrankclone.models.VerificationToken;
import com.lakhan.restprojects.hackerrankclone.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.Instant;
import java.util.Optional;

@Service
@Transactional
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersDao;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void register(@Valid RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        usersDao.save(user);

        String verificationToken = generateVerificationToken(user);
        emailService.sendMail(
                NotificationEmail.builder()
                        .subject("Please Activate your HackerRank Clone Account")
                        .body("Thank you for signing up to HackerRank Clone, " +
                                "please click on the below url to activate your account : " +
                                "http://localhost:8080/api/auth/account-verification/" + verificationToken)
                        .recepient(user.getEmail())
                        .build());
    }

    private String generateVerificationToken(User user) {
        VerificationToken verificationToken = VerificationToken.createVerificationTokenForUser(user);

        verificationTokenRepository.save(verificationToken);
        return verificationToken.getToken();
    }

    public AuthenticationResponse login(LoginRequest user) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String jwtToken = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(jwtToken)
                .expiresAt(Instant.now().plusSeconds(10))
                .username(user.getUsername())
                .build();
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new IllegalArgumentException("Invalid Token")));
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        User user = verificationToken.getUser();
        user.setRegistrationStatus(RegistrationStatus.ACTIVATED);
        usersDao.save(user);
    }
}
