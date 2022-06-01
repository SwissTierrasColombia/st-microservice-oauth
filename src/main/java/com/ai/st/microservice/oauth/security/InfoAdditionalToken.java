package com.ai.st.microservice.oauth.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.ai.st.microservice.oauth.clients.ManagerFeignClient;
import com.ai.st.microservice.oauth.clients.OperatorFeignClient;
import com.ai.st.microservice.oauth.clients.ProviderFeignClient;
import com.ai.st.microservice.oauth.clients.UserFeignClient;
import com.ai.st.microservice.oauth.dto.ManagerDto;
import com.ai.st.microservice.oauth.dto.ManagerProfileDto;
import com.ai.st.microservice.oauth.dto.OperatorDto;
import com.ai.st.microservice.oauth.dto.ProviderDto;
import com.ai.st.microservice.oauth.dto.ProviderRoleDto;
import com.ai.st.microservice.oauth.dto.RoleDto;
import com.ai.st.microservice.oauth.dto.UserDto;

@Component
public class InfoAdditionalToken implements TokenEnhancer {

    private final Logger log = LoggerFactory.getLogger(InfoAdditionalToken.class);

    @Autowired
    private UserFeignClient userClient;

    @Autowired
    private ManagerFeignClient managerClient;

    @Autowired
    private ProviderFeignClient providerClient;

    @Autowired
    private OperatorFeignClient operatorClient;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        log.info("Adding information to token");

        Map<String, Object> additionalInformation = new HashMap<>();

        UserDto userDto = userClient.findByUsername(authentication.getName());

        additionalInformation.put("first_name", userDto.getFirstName());
        additionalInformation.put("last_name", userDto.getLastName());
        additionalInformation.put("email", userDto.getEmail());
        additionalInformation.put("roles", userDto.getRoles());
        additionalInformation.put("first_login", userDto.getAmountSuccessfulLogins() == 1);

        RoleDto roleManager = userDto.getRoles().stream().filter(r -> r.getId().equals(RoleDto.ROLE_MANAGER)).findAny()
                .orElse(null);

        RoleDto roleProvider = userDto.getRoles().stream().filter(r -> r.getId().equals(RoleDto.ROLE_SUPPLY_SUPPLIER))
                .findAny().orElse(null);

        RoleDto roleOperator = userDto.getRoles().stream().filter(r -> r.getId().equals(RoleDto.ROLE_OPERATOR))
                .findAny().orElse(null);

        if (roleManager != null) {
            List<ManagerProfileDto> profilesManager = managerClient.findProfilesByUser(userDto.getId());

            ManagerProfileDto profileDirector = profilesManager.stream()
                    .filter(p -> p.getId().equals(RoleDto.SUB_ROLE_DIRECTOR_MANAGER)).findAny().orElse(null);

            ManagerDto managerDto = managerClient.findManagerByUserCode(userDto.getId());

            Boolean isDirector = profileDirector != null;
            additionalInformation.put("is_manager_director", isDirector);
            additionalInformation.put("manager_sub_roles", profilesManager);
            additionalInformation.put("entity", managerDto);
        }

        if (roleProvider != null) {

            List<ProviderRoleDto> rolesProvider = providerClient.findRolesByUser(userDto.getId());

            ProviderRoleDto roleDirector = rolesProvider.stream()
                    .filter(r -> r.getId().equals(RoleDto.SUB_ROLE_DIRECTOR_PROVIDER)).findAny().orElse(null);

            ProviderDto providerUserDto = null;
            try {
                providerUserDto = providerClient.findProviderByUserCode(userDto.getId());
            } catch (Exception ignored) {

            }

            ProviderDto providerAdminDto = null;
            try {
                providerAdminDto = providerClient.findProviderByAdministrator(userDto.getId());
            } catch (Exception ignored) {

            }

            if (providerUserDto != null) {
                additionalInformation.put("entity", providerUserDto);
            }

            if (providerAdminDto != null) {
                additionalInformation.put("entity", providerAdminDto);
            }

            Boolean isDirector = roleDirector != null;
            additionalInformation.put("is_provider_director", isDirector);
            additionalInformation.put("provider_sub_roles", rolesProvider);

        }

        if (roleOperator != null) {

            OperatorDto operatorDto = operatorClient.findOperatorByUserCode(userDto.getId());
            additionalInformation.put("entity", operatorDto);

        }

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);

        return accessToken;
    }

}
