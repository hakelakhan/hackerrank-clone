package com.lakhan.restprojects.hackerrankclone.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class CodingQuestion extends Question{

    @NotEmpty
    @Size(max = 100, min = 5, message = "Title of question should not exceed more than 100 characters and should be at least 5 characters")
    private String title;

    @NotEmpty @Size(min = 15, max = 5000, message = "Description of question should not exceed more than 5000 characters and should be at least 15 characters")
    private String description;

    @NotNull
    private long executionTimeLimitPerTestcase;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Testcase> testcases;

    @OneToMany(mappedBy = "codingQuestion")
    private Set<CodeSubmissionDetails> submissionDetails;
}
