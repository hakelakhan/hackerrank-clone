package com.lakhan.restprojects.hackerrankclone.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CodeEvaluationRequest {
    private Integer questionId;
    private String lang;
    private String source;
}
