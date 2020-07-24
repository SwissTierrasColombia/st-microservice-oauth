package com.ai.st.microservice.oauth.clients;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ai.st.microservice.oauth.dto.OperatorDto;

import feign.Feign;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

@FeignClient(name = "st-microservice-operators", configuration = OperatorFeignClient.Configuration.class)
public interface OperatorFeignClient {

	@GetMapping("api/operators/v1/users/{userCode}/operators")
	public OperatorDto findOperatorByUserCode(@PathVariable Long userCode);

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
