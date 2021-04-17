package com.lakhan.restprojects.hackerrankclone.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class CodingQuestion extends Question{

    @NotEmpty
    @Size(max = 100, min = 10, message = "Title of question should not exceed more than 100 characters and should be at least 20 characters")
    private String title;

    @NotEmpty @Size(min = 100, max = 5000, message = "Description of question should not exceed more than 5000 characters and should be at least 100 characters")
    private String description;

    @NotNull
    private long executionTimeLimitPerTestcase;

    @OneToMany
    List<Testcase> testcases;
}
