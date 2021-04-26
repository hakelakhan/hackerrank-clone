package com.lakhan.restprojects.hackerrankclone.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguraion {
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

}

