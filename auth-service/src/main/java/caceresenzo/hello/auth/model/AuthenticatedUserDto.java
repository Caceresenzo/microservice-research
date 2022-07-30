package caceresenzo.hello.auth.model;

import java.util.List;
import java.util.UUID;

import caceresenzo.hello.auth.api.apikey.ApiKeyDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticatedUserDto {
	
	private final UUID userId;
	private final List<String> authorities;
	
	public AuthenticatedUserDto(ApiKeyDto apiKey) {
		this(apiKey.getUserId(), apiKey.getScopes());
	}
	
}