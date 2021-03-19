package com.lakhan.restprojects.hackerrankclone.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Testcase {
    @Id
    @GeneratedValue
    private long id;
    private String providedInput;
    private String expectedOutput;
}
