package com.luckybox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LuckyboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuckyboxApplication.class, args);
	}
}
