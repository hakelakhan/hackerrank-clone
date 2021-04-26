package com.lakhan.restprojects.hackerrankclone.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CodeEvaluationResponse {
    private String he_id;
    private RequestStatus request_status;
    private Result result;
    private String context;
    private String status_update_url;

    @Data
    @NoArgsConstructor
    private class RequestStatus {
        private String message;
        private String code;
    }
    @Data
    @NoArgsConstructor
    private class Result {
//        private RunStatus run_status;
        private String compile_status;
    }

    @Data
    @NoArgsConstructor
    private class RunStatus {
        private String status;
    }
}
