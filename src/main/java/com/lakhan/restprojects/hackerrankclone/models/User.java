package com.lakhan.restprojects.hackerrankclone.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lakhan.restprojects.hackerrankclone.enums.RegistrationStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    @JsonIgnore
    private String password;

    private double currentScore;
    private int currentRank;

    private RegistrationStatus registrationStatus;
    private LocalDateTime createdTime;
    private LocalDateTime lastLoggedInTime;

    @OneToMany(mappedBy = "solvedBy")
    Set<CodeSubmissionDetails> submissionDetails;

    private String mobile;
    private String qualification;
    private double cgpa;
    private String university;
    private int passedOutYear;
    private String profession;
    private int experinceInYears;
    private String organization;
    private String additionalDetails;
    private String profilePictureFilename;

    private String linkedInProfile;
    private String facebookProfile;
    private String githubProfile;


    public User() {
        registrationStatus = RegistrationStatus.NOT_YET_ACTIVATED;
        createdTime = LocalDateTime.now();
        lastLoggedInTime = createdTime;
        currentScore = 0;
        submissionDetails = new HashSet<>();
    }
}
