package com.ai.st.microservice.oauth.events;

import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {

	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {

	}

}
