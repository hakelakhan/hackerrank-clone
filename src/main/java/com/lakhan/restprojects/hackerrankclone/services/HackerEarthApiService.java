package com.lakhan.restprojects.hackerrankclone.services;

import com.lakhan.restprojects.hackerrankclone.config.FeignConfiguraion;
import com.lakhan.restprojects.hackerrankclone.dtos.CodeEvaluationRequestDto;
import com.lakhan.restprojects.hackerrankclone.dtos.CodeEvaluationResponseDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="hackerEarthApiService", url = "https://api.hackerearth.com", configuration = FeignConfiguraion.class)
public interface HackerEarthApiService {
    @RequestMapping(value = "/v4/partner/code-evaluation/submissions/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @Headers("Content-Type: application/json")
    CodeEvaluationResponseDto submitCodeForEvaluation(@RequestBody CodeEvaluationRequestDto request);
}