package caceresenzo.hello.apikey.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

import caceresenzo.hello.apikey.dto.ApiKeyDto;
import lombok.Getter;

@Getter
public class RawApiKeyAuthenticationToken extends AbstractAuthenticationToken {
	
	private static final long serialVersionUID = 1L;
	
	private final ApiKeyDto apiKey;
	
	public RawApiKeyAuthenticationToken(ApiKeyDto apiKey) {
		super(AuthorityUtils.NO_AUTHORITIES);
		
		this.apiKey = apiKey;
		
		setAuthenticated(true);
	}
	
	@Override
	public Object getCredentials() {
		return apiKey;
	}
	
	@Override
	public Object getPrincipal() {
		return apiKey;
	}
	
}