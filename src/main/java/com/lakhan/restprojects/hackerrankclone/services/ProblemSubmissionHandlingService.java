package com.lakhan.restprojects.hackerrankclone.services;

import com.lakhan.restprojects.hackerrankclone.dtos.CodeEvaluationRequestDto;
import com.lakhan.restprojects.hackerrankclone.dtos.CodeEvaluationResponseDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemSubmissionHandlingService {

    @Autowired
    HackerEarthApiService hackerEarthApiService;

    @SneakyThrows
    public CodeEvaluationResponseDto submitCodeForEvaluation(CodeEvaluationRequestDto requestDto) {
        return hackerEarthApiService.submitCodeForEvaluation(requestDto);
    }
}
