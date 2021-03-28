package com.lakhan.restprojects.hackerrankclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HackerrankCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackerrankCloneApplication.class, args);
	}

}
