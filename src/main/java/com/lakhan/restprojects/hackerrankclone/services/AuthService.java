package com.lakhan.restprojects.hackerrankclone.services;

import com.lakhan.restprojects.hackerrankclone.daos.UsersRepository;
import com.lakhan.restprojects.hackerrankclone.daos.VerificationTokenRepository;
import com.lakhan.restprojects.hackerrankclone.dtos.LoginRequest;
import com.lakhan.restprojects.hackerrankclone.dtos.RegisterRequest;
import com.lakhan.restprojects.hackerrankclone.enums.RegistrationStatus;
import com.lakhan.restprojects.hackerrankclone.models.NotificationEmail;
import com.lakhan.restprojects.hackerrankclone.models.User;
import com.lakhan.restprojects.hackerrankclone.models.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@Service
@Transactional
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersDao;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private EmailService emailService;

    public void register(@Valid RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        usersDao.save(user);

        String token = generateVerificationToken(user);
        emailService.sendMail(
                NotificationEmail.builder()
                        .subject("Please Activate your HackerRank Clone Account")
                        .body("Thank you for signing up to HackerRank Clone, " +
                                "please click on the below url to activate your account : " +
                                "http://localhost:8080/api/auth/account-verification/" + token)
                        .recepient(user.getEmail())
                        .build());
    }

    private String generateVerificationToken(User user) {
        VerificationToken verificationToken = VerificationToken.createVerificationTokenForUser(user);

        verificationTokenRepository.save(verificationToken);
        return verificationToken.getToken();
    }

    public void login(LoginRequest user) {
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
