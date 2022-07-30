package caceresenzo.hello.apikey.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfiguration {
	
	@Bean
	public OpenAPI openApi() {
		return new OpenAPI();
	}
	
}