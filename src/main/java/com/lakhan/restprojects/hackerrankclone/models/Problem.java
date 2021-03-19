package com.lakhan.restprojects.hackerrankclone.models;

import com.lakhan.restprojects.hackerrankclone.enums.DifficultyLevel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
public class Problem {
    @Id
    @GeneratedValue
    private int problemId;

    @NotEmpty
    private String problemStatement;
    @NotEmpty
    private String problemDescription;
    private String hint;

    @NotEmpty
    private DifficultyLevel difficultyLevel;
    
    @ManyToOne
    private User postedByUser;
    
    @NotEmpty
    private long maxScore;

    @NotEmpty
    private long executionTimeLimitPerTestcase;

    @OneToMany
    List<Testcase> testcases;

    @ManyToMany
    List<Topic> problemBasedOnTopics;
}
