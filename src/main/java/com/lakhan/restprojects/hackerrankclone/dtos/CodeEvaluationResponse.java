package com.lakhan.restprojects.hackerrankclone.dtos;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeEvaluationResponse {
    private String compilationErrorMessage;

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
        private String memory;
        private String cpuTime;
        private Boolean testcasePassed;
    }
}
