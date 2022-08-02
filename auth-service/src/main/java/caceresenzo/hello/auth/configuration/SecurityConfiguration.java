package caceresenzo.hello.auth.configuration;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;

import caceresenzo.hello.auth.api.apikey.ApiKeyServiceClient;
import caceresenzo.hello.auth.filter.ApiKeyAuthenticationFilter;
import caceresenzo.hello.auth.util.KeycloakGrantedAuthoritiesExtractor;
import caceresenzo.hello.common.security.configuration.BaseApplicationSecurity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends BaseApplicationSecurity {
	
	private final ObjectProvider<ApiKeyServiceClient> apiKeyServiceClient;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		
		http.oauth2ResourceServer()
			.authenticationEntryPoint(unauthorizedEntryPoint)
			.jwt().jwtAuthenticationConverter(createJwtAuthenticationConverter());
	}
	
	@Override
	protected void configureFilters(HttpSecurity http) {
		http.addFilterBefore(new ApiKeyAuthenticationFilter(apiKeyServiceClient), BearerTokenAuthenticationFilter.class);
	}
	
	public static JwtAuthenticationConverter createJwtAuthenticationConverter() {
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakGrantedAuthoritiesExtractor());
		
		return jwtAuthenticationConverter;
	}
	
}