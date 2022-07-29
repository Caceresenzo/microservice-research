package caceresenzo.hello.common.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

@Getter
public class ServiceAuthenticationToken extends AbstractAuthenticationToken {
	
	private static final long serialVersionUID = 1L;
	
	private final String serviceName;
	
	public ServiceAuthenticationToken(String serviceName, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		
		this.serviceName = serviceName;
		
		super.setAuthenticated(true);
	}
	
	@Override
	public Object getCredentials() {
		return serviceName;
	}
	
	@Override
	public Object getPrincipal() {
		return String.format("service/%s", serviceName);
	}
	
}