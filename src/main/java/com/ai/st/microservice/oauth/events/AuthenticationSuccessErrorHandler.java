package com.ai.st.microservice.oauth.events;

import com.ai.st.microservice.oauth.clients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

    private final Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

    @Autowired
    private UserFeignClient userClient;

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        try {
            userClient.updateLastLogin(authentication.getName());
            log.info(String.format("It has been updated last login for user %s", authentication.getName()));
        } catch (Exception e) {
            log.error(String.format("Error updating last login for user %s", authentication.getName()));
        }
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        log.error(String.format("An error has occurred during authentication for user %s", authentication.getName()), exception);
    }

}
