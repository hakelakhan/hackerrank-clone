package com.lakhan.restprojects.hackerrankclone.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JdoodleCodeEvaluationResponse {
        private String output;
        private String memory;
        private String cpuTime;
        private Integer statusCode;
}
