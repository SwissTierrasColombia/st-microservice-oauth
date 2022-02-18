package com.ai.st.microservice.oauth.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class 	AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private InfoAdditionalToken infoAdditionalToken;

	@Value("${oauth.clients.site.id}")
	private String siteWebId;

	@Value("${oauth.clients.site.secret}")
	private String siteWebSecret;

	@Value("${oauth.clients.site.timeAccessToken}")
	private Integer siteTimeAccessToken;

	@Value("${oauth.clients.site.timeRefreshToken}")
	private Integer siteTimeRefreshToken;

	@Value("${oauth.jwt.key}")
	private String jwtKey;

	@Value("${oauth.clients.assistant.id}")
	private String assistantId;

	@Value("${oauth.clients.assistant.secret}")
	private String assistantSecret;

	@Value("${oauth.clients.assistant.timeAccessToken}")
	private Integer assistantTimeAccessToken;

	@Value("${oauth.clients.assistant.timeRefreshToken}")
	private Integer assistantTimeRefreshToken;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				// oauth site web
				.withClient(siteWebId).secret(passwordEncoder.encode(siteWebSecret)).scopes("read", "write")
				.authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(siteTimeAccessToken)
				.refreshTokenValiditySeconds(siteTimeRefreshToken).and()
				// oauth assistant
				.withClient(assistantId).secret(passwordEncoder.encode(assistantSecret)).scopes("read", "write")
				.authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(assistantTimeAccessToken)
				.refreshTokenValiditySeconds(assistantTimeRefreshToken);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdditionalToken, accessTokenConverter()));

		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.accessTokenConverter(accessTokenConverter()).tokenEnhancer(tokenEnhancerChain);
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(jwtKey);
		return tokenConverter;
	}

}
