package com.ai.st.microservice.oauth.services;

import java.util.ArrayList;
import java.util.List;

import com.ai.st.microservice.oauth.services.tracing.SCMTracing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ai.st.microservice.oauth.clients.UserFeignClient;
import com.ai.st.microservice.oauth.dto.RoleDto;
import com.ai.st.microservice.oauth.dto.UserDto;

import feign.FeignException;

@Service
public class UserService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {

            UserDto userDto = userClient.findByUsername(username);

            List<GrantedAuthority> authorities = new ArrayList<>();
            if (userDto.getRoles().size() > 0) {
                for (RoleDto roleDto : userDto.getRoles()) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + roleDto.getName().replace(" ", "_")));
                }
            }

            return new User(userDto.getUsername(), userDto.getPassword(), userDto.getEnabled(), true, true, true,
                    authorities);

        } catch (FeignException e) {
            String messageError = String.format("Error consultando el usuario para iniciar sesión %s : %s", username,
                    e.getMessage());
            SCMTracing.sendError(messageError);
            log.error(messageError);
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
    }

}
