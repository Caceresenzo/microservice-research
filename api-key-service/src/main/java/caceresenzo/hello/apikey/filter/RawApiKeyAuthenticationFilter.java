package caceresenzo.hello.apikey.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import caceresenzo.hello.apikey.dto.ApiKeyDto;
import caceresenzo.hello.apikey.security.token.RawApiKeyAuthenticationToken;
import caceresenzo.hello.apikey.service.ApiKeyService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RawApiKeyAuthenticationFilter extends OncePerRequestFilter {
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String AUTHORIZATION_TYPE = "API-Key";
	
	private final ApiKeyService service;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String plain = extractApiKey(request);
		
		System.out.println("RawApiKeyAuthenticationFilter.doFilterInternal()");
		
		if (plain != null) {
			Optional<ApiKeyDto> apiKey = service.findByPlain(plain);
			
			if (apiKey.isPresent()) {
				SecurityContextHolder.getContext().setAuthentication(new RawApiKeyAuthenticationToken(apiKey.get()));
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
//	@Override
//	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//		return SecurityContextHolder.getContext().getAuthentication() != null;
//	}
	
	public static String extractApiKey(HttpServletRequest request) {
		return extractApiKey(request.getHeader(AUTHORIZATION_HEADER));
	}
	
	public static String extractApiKey(String header) {
		if (header != null) {
			String[] parts = header.trim().split(" ", 2);
			
			if (parts.length == 2 && AUTHORIZATION_TYPE.equalsIgnoreCase(parts[0])) {
				return parts[1];
			}
		}
		
		return null;
	}
	
}