package caceresenzo.hello.common.security.filter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import caceresenzo.hello.common.security.authentication.AuthenticationType;
import caceresenzo.hello.common.security.token.ServiceAuthenticationToken;
import caceresenzo.hello.common.security.token.UserAuthenticationToken;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeaderAuthenticationFilter extends OncePerRequestFilter {
	
	public static final String AUTHENTICATION_TYPE_HEADER = "X-Authentication-Type";
	public static final String SERVICE_NAME_HEADER = "X-Service-Name";
	public static final String USER_ID_HEADER = "X-User-ID";
	public static final String USER_AUTHORITIES_HEADER = "X-Authorities";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		Authentication authentication = extractAuthentication(request);
		
		if (authentication != null) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return SecurityContextHolder.getContext().getAuthentication() != null;
	}
	
	public static AuthenticationType extractAuthenticationType(HttpServletRequest request) {
		return AuthenticationType.parse(request.getHeader(AUTHENTICATION_TYPE_HEADER));
	}
	
	public static ServiceAuthenticationToken extractServiceAuthenticationToken(HttpServletRequest request) {
		String rawUserId = request.getHeader(USER_ID_HEADER);
		String serviceName = request.getHeader(SERVICE_NAME_HEADER);
		
		try {
			UUID userId = UUID.fromString(rawUserId);
			
			if (StringUtils.hasText(serviceName)) {
				return new ServiceAuthenticationToken(serviceName, userId, AuthorityUtils.NO_AUTHORITIES);
			}
		} catch (Exception exception) {
			return null;
		}
		
		return null;
	}
	
	public static UserAuthenticationToken extractUserAuthenticationToken(HttpServletRequest request) {
		String rawUserId = request.getHeader(USER_ID_HEADER);
		String rawAuthorities = request.getHeader(USER_AUTHORITIES_HEADER);
		
		try {
			UUID userId = UUID.fromString(rawUserId);
			List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(rawAuthorities);
			
			return new UserAuthenticationToken(userId, authorities);
		} catch (Exception exception) {
			return null;
		}
	}
	
	public static Authentication extractAuthentication(HttpServletRequest request) {
		AuthenticationType type = AuthenticationType.parse(request.getHeader(AUTHENTICATION_TYPE_HEADER));
		if (type == null) {
			return null;
		}
		
		switch (type) {
			case SERVICE: {
				return extractServiceAuthenticationToken(request);
			}
			
			case USER: {
				return extractUserAuthenticationToken(request);
			}
			
			default: {
				log.warn("Unsupported authentication type: {}", type);
				return null;
			}
		}
	}
	
}