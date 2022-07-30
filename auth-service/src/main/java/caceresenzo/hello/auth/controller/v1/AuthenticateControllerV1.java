package caceresenzo.hello.auth.controller.v1;

import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import caceresenzo.hello.auth.api.apikey.ApiKeyServiceClient;
import caceresenzo.hello.auth.api.apikey.ApiKeyDto;
import caceresenzo.hello.auth.model.AuthenticatedUserDto;
import caceresenzo.hello.common.web.exception.ForbiddenException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping(path = "/v1/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticateControllerV1 {
	
	private final ApiKeyServiceClient apiKeyServiceClient;
	
	@GetMapping
	public AuthenticatedUserDto authenticate(@RequestHeader("Authorization") String authorization) {
		if (!StringUtils.hasText(authorization)) {
			throw new ForbiddenException();
		}
		
		String[] parts = authorization.split(" ");
		if (parts.length != 2) {
			throw new ForbiddenException();
		}
		
		String type = parts[0];
		
		if ("API-Key".equalsIgnoreCase(type)) {
			try {
				ApiKeyDto apiKey = apiKeyServiceClient.getSelf(authorization);
				
				return new AuthenticatedUserDto(apiKey);
			} catch (FeignException.Forbidden exception) {
				throw new ForbiddenException();
			}
		}
		
		throw new UnsupportedOperationException(type);
	}
	
}