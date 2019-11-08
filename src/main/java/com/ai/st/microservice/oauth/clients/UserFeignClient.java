package com.ai.st.microservice.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ai.st.microservice.oauth.dto.UserDto;

@FeignClient(name = "st-microservice-administration")
public interface UserFeignClient {

	@GetMapping("/api/administration/users/login")
	public UserDto findByUsername(@RequestParam String username);

}
