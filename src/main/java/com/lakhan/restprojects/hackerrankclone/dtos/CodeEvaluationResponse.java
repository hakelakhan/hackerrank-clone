package com.lakhan.restprojects.hackerrankclone.dtos;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeEvaluationResponse {
    private String errorMessage;
    private String warningMessage;
    private boolean allTestcasesPassed;
    private int passedTestcases;
    private int totalTestcases;
    private double score;
    private double maxScore;

    @Singular
    private List<TestcaseResult> testcaseResults;


    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class TestcaseResult {
        private Integer id;
        private String input;
        private String output;
        private String expectedOutput;
        private String memory;
        private String cpuTime;
        private Boolean testcasePassed;
    }
}
