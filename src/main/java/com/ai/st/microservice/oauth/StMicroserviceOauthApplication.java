package com.ai.st.microservice.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class StMicroserviceOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(StMicroserviceOauthApplication.class, args);
	}

}
