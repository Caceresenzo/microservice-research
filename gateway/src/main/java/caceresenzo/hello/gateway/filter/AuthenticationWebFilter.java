package caceresenzo.hello.gateway.filter;

import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import caceresenzo.hello.gateway.api.auth.AuthenticatedUserDto;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AuthenticationWebFilter implements WebFilter {
	
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
				.header(HttpHeaders.AUTHORIZATION, authorization)
				.exchangeToMono(this::handleResponse))
			.map((authenticatedUser) -> applyHeaders(exchange, authenticatedUser))
			.switchIfEmpty(Mono.defer(() -> Mono.just(exchange)))
			.map(AuthenticationWebFilter::removeOtherHeaders)
			.flatMap(chain::filter);
	}
	
	public Mono<String> extractAuthorization(ServerWebExchange exchange) {
		return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION));
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
	
	public static ServerWebExchange removeHeaders(ServerWebExchange exchange) {
		return exchange.mutate()
			.request((request) -> request.headers((headers) -> {
				headers.remove(AUTHENTICATION_TYPE_HEADER);
				headers.remove(USER_ID_HEADER);
				headers.remove(USER_AUTHORITIES_HEADER);
			}))
			.build();
	}
	
	public static ServerWebExchange removeOtherHeaders(ServerWebExchange exchange) {
		return exchange.mutate()
			.request((request) -> request.headers((headers) -> {
				headers.remove(HttpHeaders.COOKIE);
				headers.remove(HttpHeaders.SET_COOKIE);
				headers.remove(HttpHeaders.AUTHORIZATION);
			}))
			.build();
	}
	
}