package caceresenzo.hello.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import caceresenzo.hello.auth.api.apikey.ApiKeyDto;
import caceresenzo.hello.auth.api.apikey.ApiKeyServiceClient;
import caceresenzo.hello.common.security.token.UserAuthenticationToken;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {
	
	private static final String API_KEY_TYPE = "API-Key";
	
	private final ObjectProvider<ApiKeyServiceClient> apiKeyServiceClient;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		Authentication authentication = authenticate(request);
		
		if (authentication != null) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}
	
	public Authentication authenticate(HttpServletRequest request) {
		String authorization = extractAuthorizationHeader(request);
		
		String[] parts = splitAuthorization(authorization);
		if (parts == null) {
			return null;
		}
		
		if (!API_KEY_TYPE.equalsIgnoreCase(parts[0])) {
			return null;
		}
		
		try {
			ApiKeyDto apiKey = apiKeyServiceClient.getObject().getSelf(authorization);
			
			return toToken(apiKey);
		} catch (FeignException.Unauthorized | FeignException.Forbidden exception) {
			return null;
		}
	}
	
	public static String extractAuthorizationHeader(HttpServletRequest request) {
		return request.getHeader(HttpHeaders.AUTHORIZATION);
	}
	
	public static String[] splitAuthorization(String authorization) {
		if (!StringUtils.hasText(authorization)) {
			return null;
		}
		
		String[] parts = authorization.split(" ");
		if (parts.length != 2) {
			return null;
		}
		
		return parts;
	}
	
	public static UserAuthenticationToken toToken(ApiKeyDto apiKey) {
		return UserAuthenticationToken.fromStrings(apiKey.getUserId(), apiKey.getScopes());
	}
	
}