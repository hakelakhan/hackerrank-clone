package com.lakhan.restprojects.hackerrankclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@EnableAsync
public class HackerrankCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackerrankCloneApplication.class, args);
	}
	//TODO Implement Refresh Token
	//TODO Implement Logout
}
