package com.lakhan.restprojects.hackerrankclone.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class Testcase {
    @Id
    @GeneratedValue
    private long id;
    private String providedInput;
    @NotEmpty
    private String expectedOutput;

    public boolean passes(String actualOutput) {
        return this.expectedOutput.equals(actualOutput);
    }
}
