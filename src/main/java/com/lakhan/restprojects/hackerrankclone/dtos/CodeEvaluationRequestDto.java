package com.lakhan.restprojects.hackerrankclone.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class CodeEvaluationRequestDto {
    private String lang;
    private String source;
    private String input;
    private int memory_limit;
    private String time_limit;
    private String context;
    private String callback;
}
