package com.ai.st.microservice.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.ai.st.microservice.oauth.clients.UserFeignClient;
import com.ai.st.microservice.oauth.dto.UserDto;

@Component
public class InfoAdditionalToken implements TokenEnhancer {

	@Autowired
	private UserFeignClient userClient;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		Map<String, Object> additionalInformation = new HashMap<String, Object>();

		UserDto userDto = userClient.findByUsername(authentication.getName());
		additionalInformation.put("first_name", userDto.getFirstName());
		additionalInformation.put("last_name", userDto.getLastName());
		additionalInformation.put("email", userDto.getEmail());
		additionalInformation.put("roles", userDto.getRoles());
		

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);

		return accessToken;
	}

}
