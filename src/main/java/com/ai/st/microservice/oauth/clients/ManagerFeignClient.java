package com.ai.st.microservice.oauth.clients;

import java.util.List;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ai.st.microservice.oauth.dto.ManagerProfileDto;

import feign.Feign;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

@FeignClient(name = "st-microservice-managers", configuration = ManagerFeignClient.Configuration.class)
public interface ManagerFeignClient {

	@GetMapping("/api/managers/v1/users/{userCode}/profiles")
	public List<ManagerProfileDto> findProfilesByUser(@PathVariable Long userCode);

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
