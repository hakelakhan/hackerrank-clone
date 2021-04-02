package com.lakhan.restprojects.hackerrankclone.services;

import com.lakhan.restprojects.hackerrankclone.config.FeignConfiguraion;
import com.lakhan.restprojects.hackerrankclone.dtos.CodeEvaluationRequest;
import com.lakhan.restprojects.hackerrankclone.dtos.CodeEvaluationResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="hackerEarthApiService", url = "https://api.hackerearth.com", configuration = FeignConfiguraion.class)
public interface HackerEarthApiService {
    @RequestMapping(value = "/v4/partner/code-evaluation/submissions/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @Headers("Content-Type: application/json")
    CodeEvaluationResponse submitCodeForEvaluation(@RequestBody CodeEvaluationRequest request);

    @RequestMapping(value = "/v4/partner/code-evaluation/submissions/{submission-id}", method = RequestMethod.GET, produces = "application/json")
    @Headers("Accept : application/json")
    String getStatusOfSubmittedCode(@PathVariable String submissionId);
    }
