package com.ai.st.microservice.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ai.st.microservice.oauth.dto.UserDto;

@FeignClient(name = "st-microservice-administration")
public interface UserFeignClient {

    @GetMapping("/api/administration/v1/users/login")
    UserDto findByUsername(@RequestParam(name = "username") String username);

    @PutMapping("/api/administration/v1/users/update-last-login")
    UserDto updateLastLogin(@RequestParam(name = "username") String username);

}
