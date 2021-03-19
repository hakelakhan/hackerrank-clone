package com.lakhan.restprojects.hackerrankclone.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class VerificationLink {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private User user;
    String randomVerificationLink;
    LocalDateTime expiryTime;
}
