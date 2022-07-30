package caceresenzo.hello.common.security.token;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

@Getter
public class ServiceAuthenticationToken extends AbstractAuthenticationToken {
	
	private static final long serialVersionUID = 1L;
	
	private final String serviceName;
	private final UUID userId;
	
	public ServiceAuthenticationToken(String serviceName, Collection<? extends GrantedAuthority> authorities) {
		this(serviceName, null, authorities);
	}
	
	public ServiceAuthenticationToken(String serviceName, UUID userId, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		
		this.serviceName = serviceName;
		this.userId = userId;
		
		super.setAuthenticated(true);
	}
	
	@Override
	public Object getCredentials() {
		if (hasUser()) {
			return String.format("%s/%s", serviceName, userId);
		} else {
			return serviceName;
		}
	}
	
	@Override
	public Object getPrincipal() {
		if (hasUser()) {
			return String.format("service/%s/user/%s", serviceName, userId);
		} else {
			return String.format("service/%s", serviceName);
		}
	}
	
	public boolean hasUser() {
		return userId != null;
	}
	
}