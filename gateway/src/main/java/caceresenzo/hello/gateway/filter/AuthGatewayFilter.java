package caceresenzo.hello.gateway.filter;

import java.util.stream.Collectors;

import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import caceresenzo.hello.gateway.api.auth.AuthenticatedUserDto;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AuthGatewayFilter implements WebFilter {
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String AUTHENTICATION_TYPE_HEADER = "X-Authentication-Type";
	public static final String USER_ID_HEADER = "X-User-ID";
	public static final String USER_AUTHORITIES_HEADER = "X-Authorities";
	public static final String USER_AUTHENTICATION_TYPE = "USER";
	
	private final String authenticateEndpoint;
	private final WebClient webClient;
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		return extractAuthorization(exchange)
			.flatMap((authorization) -> webClient.get()
				.uri(authenticateEndpoint)
				.header(AUTHORIZATION_HEADER, authorization)
				.exchangeToMono(this::handleResponse))
			.map((authenticatedUser) -> applyHeaders(exchange, authenticatedUser))
			.defaultIfEmpty(exchange)
			.flatMap(chain::filter);
	}
	
	public Mono<String> extractAuthorization(ServerWebExchange exchange) {
		return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(AUTHORIZATION_HEADER));
	}
	
	public Mono<AuthenticatedUserDto> handleResponse(ClientResponse response) {
		if (response.statusCode().is2xxSuccessful()) {
			return response.bodyToMono(AuthenticatedUserDto.class);
		}
		
		return Mono.empty();
	}
	
	public static ServerWebExchange applyHeaders(ServerWebExchange exchange, AuthenticatedUserDto authenticatedUser) {
		return exchange.mutate()
			.request((request) -> request
				.header(AUTHENTICATION_TYPE_HEADER, USER_AUTHENTICATION_TYPE)
				.header(USER_ID_HEADER, authenticatedUser.getUserId().toString())
				.header(USER_AUTHORITIES_HEADER, authenticatedUser.getAuthorities().stream().collect(Collectors.joining(","))))
			.build();
	}
	
}