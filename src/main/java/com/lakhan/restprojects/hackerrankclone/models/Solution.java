package com.lakhan.restprojects.hackerrankclone.models;

import com.lakhan.restprojects.hackerrankclone.enums.ProgrammingLanguageUsed;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Solution {
    @Id  @GeneratedValue
    long id;

    @ManyToOne
    CodingQuestion codingQuestion;
    String codeWrittenByUser;
    ProgrammingLanguageUsed programmingLanguageUsed;
    private boolean compiled;
    private boolean status;//Solved, Partially Solved, Not Attempted yet
//    private User solutionPostedBy;
    private int pointsReceivedForSolution;
 //   private CodeEvaluationJdoodleApiResponseDto codeEvaluationJdoodleApiResponseDto;
}
