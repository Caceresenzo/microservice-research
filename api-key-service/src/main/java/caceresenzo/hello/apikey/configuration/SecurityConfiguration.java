package caceresenzo.hello.apikey.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import caceresenzo.hello.apikey.filter.RawApiKeyAuthenticationFilter;
import caceresenzo.hello.apikey.service.ApiKeyService;
import caceresenzo.hello.common.security.configuration.BaseApplicationSecurity;
import caceresenzo.hello.common.security.filter.HeaderAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends BaseApplicationSecurity {
	
	private final ApiKeyService service;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		
		http.addFilterBefore(new RawApiKeyAuthenticationFilter(service), HeaderAuthenticationFilter.class);
	}
	
}