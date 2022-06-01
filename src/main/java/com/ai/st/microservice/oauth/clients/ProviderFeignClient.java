package com.ai.st.microservice.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ai.st.microservice.oauth.dto.ProviderDto;
import com.ai.st.microservice.oauth.dto.ProviderRoleDto;

import feign.Feign;
import feign.codec.Encoder;

import feign.form.spring.SpringFormEncoder;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;

import java.util.List;

@FeignClient(name = "st-microservice-providers", configuration = ProviderFeignClient.Configuration.class)
public interface ProviderFeignClient {

    @GetMapping("/api/providers-supplies/v1/administrators/{userCode}/roles")
    List<ProviderRoleDto> findRolesByUser(@PathVariable Long userCode);

    @GetMapping("/api/providers-supplies/v1/users/{userCode}/providers")
    ProviderDto findProviderByUserCode(@PathVariable Long userCode);

    @GetMapping("/api/providers-supplies/v1/administrators/{userCode}/providers")
    ProviderDto findProviderByAdministrator(@PathVariable Long userCode);

    class Configuration {

        @Bean
        Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
            return new SpringFormEncoder(new SpringEncoder(converters));
        }

        @Bean
        @Scope("prototype")
        public Feign.Builder feignBuilder() {
            return Feign.builder();
        }

    }

}
