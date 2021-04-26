package com.lakhan.restprojects.hackerrankclone.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeEvaluationRequest {
    private String lang;
    private String source;
    private String input;
    private int memory_limit;
    private String time_limit;
    private String context;
    private String callback;
}
