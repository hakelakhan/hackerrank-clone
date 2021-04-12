package com.lakhan.restprojects.hackerrankclone.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class AuthInterceptor  implements RequestInterceptor {

    @Value("${hacker-earth.api.client-secret}")
    private String clientSecret;

    @Override
    public void apply(RequestTemplate requestTemplate) {
          requestTemplate.header("client-secret", clientSecret);
    }
}
