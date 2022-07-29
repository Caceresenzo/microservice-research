package caceresenzo.hello.common.security;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

@Getter
public class UserAuthenticationToken extends AbstractAuthenticationToken {
	
	private static final long serialVersionUID = 1L;
	
	private final UUID userId;
	
	public UserAuthenticationToken(UUID userId, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		
		this.userId = Objects.requireNonNull(userId, "userId");
		
		super.setAuthenticated(true);
	}
	
	@Override
	public Object getCredentials() {
		return userId.toString();
	}
	
	@Override
	public Object getPrincipal() {
		return String.format("user/%s", userId);
	}
	
}