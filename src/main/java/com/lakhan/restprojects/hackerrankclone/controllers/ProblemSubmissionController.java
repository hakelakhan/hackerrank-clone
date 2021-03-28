package com.lakhan.restprojects.hackerrankclone.controllers;

import com.lakhan.restprojects.hackerrankclone.dtos.CodeEvaluationRequestDto;
import com.lakhan.restprojects.hackerrankclone.services.ProblemSubmissionHandlingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProblemSubmissionController {

    @Autowired
    ProblemSubmissionHandlingService submissionHandlingService;

    @PostMapping("/problem/submit")
    public void submitCode(@RequestBody CodeEvaluationRequestDto requestDto) {
        log.info("Submitting code ");
        submissionHandlingService.submitCodeForEvaluation(requestDto);
    }
}
