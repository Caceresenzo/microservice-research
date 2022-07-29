package caceresenzo.hello.apikey;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import caceresenzo.hello.common.security.UserAuthenticationToken;

public class CustomFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		SecurityContextHolder.getContext().setAuthentication(new UserAuthenticationToken(UUID.randomUUID(), Collections.singletonList(new SimpleGrantedAuthority("qsd"))));
		filterChain.doFilter(request, response);
	}
	
}