package com.lakhan.restprojects.hackerrankclone.models;

import com.lakhan.restprojects.hackerrankclone.enums.ProgrammingLanguageUsed;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Data
@Entity
public class Solution {
    @Id  @GeneratedValue
    long id;

    @ManyToOne
    Problem problem;
    String codeWrittenByUser;
    ProgrammingLanguageUsed programmingLanguageUsed;
    private boolean compiled;
    private boolean status;//Solved, Partially Solved, Not Attempted yet
    private User solutionPostedBy;
    private int pointsReceivedForSolution;
    List<TestcaseExecutionDetails> testcaseExecutionDetails;
}
