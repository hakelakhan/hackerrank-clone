package com.lakhan.restprojects.hackerrankclone.services;

import com.lakhan.restprojects.hackerrankclone.config.FeignConfiguraion;
import com.lakhan.restprojects.hackerrankclone.dtos.JdoodleCodeEvaluationResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name="jdoodleApiService", url = "https://api.jdoodle.com", configuration = FeignConfiguraion.class)
public interface JdoodleApiService {
    @RequestMapping(value = "/v1/execute", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @Headers("Content-Type: application/json")
    JdoodleCodeEvaluationResponse submitCodeForEvaluation(@RequestBody String request);
    }
