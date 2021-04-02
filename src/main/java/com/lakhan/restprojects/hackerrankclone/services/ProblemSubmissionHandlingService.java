package com.lakhan.restprojects.hackerrankclone.services;

import com.lakhan.restprojects.hackerrankclone.dtos.CodeEvaluationRequest;
import com.lakhan.restprojects.hackerrankclone.dtos.CodeEvaluationResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemSubmissionHandlingService {

    @Autowired
    HackerEarthApiService hackerEarthApiService;

    @SneakyThrows
    public CodeEvaluationResponse submitCodeForEvaluation(CodeEvaluationRequest requestDto) {
        return hackerEarthApiService.submitCodeForEvaluation(requestDto);
    }
}
