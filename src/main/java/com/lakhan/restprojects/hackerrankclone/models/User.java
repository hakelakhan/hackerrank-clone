package com.lakhan.restprojects.hackerrankclone.models;

import com.lakhan.restprojects.hackerrankclone.enums.RegistrationStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public
class User {
    @Id @GeneratedValue
    private long userId;

    @NotEmpty
    private String username;

    @Email
    private String emailId;

    @NotEmpty @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    private int currentScore;
    private int currentRank;

    private RegistrationStatus registrationStatus;
    private LocalDateTime createdTime;
    private LocalDateTime lastLoggedInTime;
}
