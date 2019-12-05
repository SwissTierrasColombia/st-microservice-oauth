package com.ai.st.microservice.oauth.services;

import com.ai.st.microservice.oauth.dto.UserDto;

public interface IUserService {

	public UserDto findByUsername(String username);

}
