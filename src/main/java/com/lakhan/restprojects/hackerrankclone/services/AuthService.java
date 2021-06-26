package com.lakhan.restprojects.hackerrankclone.services;

import com.lakhan.restprojects.hackerrankclone.config.AppConfig;
import com.lakhan.restprojects.hackerrankclone.daos.UsersRepository;
import com.lakhan.restprojects.hackerrankclone.daos.VerificationTokenRepository;
import com.lakhan.restprojects.hackerrankclone.dtos.AuthenticationResponse;
import com.lakhan.restprojects.hackerrankclone.dtos.LoginRequest;
import com.lakhan.restprojects.hackerrankclone.dtos.RefreshTokenRequest;
import com.lakhan.restprojects.hackerrankclone.dtos.RegisterRequest;
import com.lakhan.restprojects.hackerrankclone.enums.RegistrationStatus;
import com.lakhan.restprojects.hackerrankclone.models.NotificationEmail;
import com.lakhan.restprojects.hackerrankclone.models.RefreshToken;
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
    private AppConfig appConfig;

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

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public void register(@Valid RegisterRequest registerRequest) {
        User user = new User();
        user.setFullName(registerRequest.getFullName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        usersDao.saveAndFlush(user);

        String verificationToken = generateVerificationToken(user);
        emailService.sendMail(
                NotificationEmail.builder()
                        .subject("Please Activate your Codie Account")
                        .body("Thank you for signing up to Codie, " +
                                "please click on the below url to activate your account : " +
                                appConfig.getUrl() + "api/auth/account-verification/" + verificationToken)
                        .recepient(user.getEmail())
                        .build());
    }

    private String generateVerificationToken(User user) {
        VerificationToken verificationToken = VerificationToken.createVerificationTokenForUser(user);

        verificationTokenRepository.saveAndFlush(verificationToken);
        return verificationToken.getToken();
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String jwtToken = jwtProvider.generateToken(authenticate);
        RefreshToken refreshToken = refreshTokenService.generateToken();
        User user = findByEmail(loginRequest.getEmail());
        return AuthenticationResponse.builder()
                .authenticationToken(jwtToken)
                .expiresAt(Instant.now().plusSeconds(jwtProvider.getJwtTokenExpirationSecs()))      //TODO remove this field if not needed
                .username(user.getFullName())
                .refreshToken(refreshToken.getToken())
                .score(user.getCurrentScore())
                .email(loginRequest.getEmail())
                .build();
    }

    public User findByEmail(String email) {
        return usersDao.findByEmail(email).orElseThrow( () -> new RuntimeException("User details not available"));
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new IllegalArgumentException("Invalid Token")));
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        User user = verificationToken.getUser();
        user.setRegistrationStatus(RegistrationStatus.ACTIVATED);
        usersDao.saveAndFlush(user);
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateToken(refreshTokenRequest.getRefreshToken());
        String newJwtToken = jwtProvider.generateTokenWithEmail(refreshTokenRequest.getEmail());
        User user = findByEmail(refreshTokenRequest.getEmail());
        return AuthenticationResponse.builder()
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .score(user.getCurrentScore())
                .expiresAt(Instant.now().plusSeconds(jwtProvider.getJwtTokenExpirationSecs()))
                .username(user.getFullName())
                .authenticationToken(newJwtToken)
                .build();
    }

    public void logout(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteToken(refreshTokenRequest.getRefreshToken());
    }
}
