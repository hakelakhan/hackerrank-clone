package com.lakhan.restprojects.hackerrankclone.controllers;

import com.lakhan.restprojects.hackerrankclone.dtos.CodeEvaluationRequest;
import com.lakhan.restprojects.hackerrankclone.dtos.CodeEvaluationResponse;
import com.lakhan.restprojects.hackerrankclone.services.ProblemSubmissionHandlingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/submissions")
public class ProblemSubmissionController {

    @Autowired
    ProblemSubmissionHandlingService problemSubmissionHandlingService;

    @PostMapping("/problem")
    public ResponseEntity<CodeEvaluationResponse> submitCode(@RequestBody CodeEvaluationRequest requestDto) {
        log.info("Handling Code submitted for question id " + requestDto.getQuestionId());
        CodeEvaluationResponse response = problemSubmissionHandlingService.submitCodeForEvaluation(requestDto);
        if(response == null) {
            log.error("Could not find question with id " + requestDto.getQuestionId());
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(response);
    }

}
