package com.lakhan.restprojects.hackerrankclone.models;

import lombok.Data;

import javax.persistence.*;
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
