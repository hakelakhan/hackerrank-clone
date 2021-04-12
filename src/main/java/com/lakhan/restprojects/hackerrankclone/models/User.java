package com.lakhan.restprojects.hackerrankclone.models;

import com.lakhan.restprojects.hackerrankclone.enums.RegistrationStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
public
class User {
    @Id @GeneratedValue
    private long userId;

    @NotEmpty
    private String fullName;

    @Email(message = "Email Id must be valid")
    @Column(name="email", unique = true)
    private String email;

    @NotEmpty @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    private int currentScore;
    private int currentRank;

    private RegistrationStatus registrationStatus;
    private LocalDateTime createdTime;
    private LocalDateTime lastLoggedInTime;

    public User() {
        registrationStatus = RegistrationStatus.NOT_YET_ACTIVATED;
        createdTime = LocalDateTime.now();
        lastLoggedInTime = createdTime;
        currentScore = 0;
    }
}
