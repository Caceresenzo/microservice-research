package caceresenzo.hello.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.reactive.function.client.WebClient;

import caceresenzo.hello.gateway.api.auth.AuthenticationServiceConfiguration;
import caceresenzo.hello.gateway.filter.AuthGatewayFilter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class RoutesConfiguration {
	
	@Bean
	@Lazy
	AuthGatewayFilter authGatewayFilterFactory(WebClient.Builder webClientBuilder, AuthenticationServiceConfiguration serviceConfiguration) {
		WebClient webClient = webClientBuilder
			.baseUrl(serviceConfiguration.getBaseUrl())
			.build();
		
		return new AuthGatewayFilter(serviceConfiguration.getAuthenticateEndpoint(), webClient);
	}
	
	@Bean
	RouteLocator routes(RouteLocatorBuilder builder, AuthGatewayFilter authGatewayFilter) {
		return builder.routes()
			.route(route -> route
				.path("/")
				.uri("lb://index"))
			.route(route -> route
				.path("/graphql", "/graphiql")
				.uri("lb://graphql"))
			.route(route -> route
				.path("/v1/api-keys", "/v1/api-keys/*")
				.uri("lb://api-key-service"))
			.route(route -> route
				.path("/v1/users", "/v1/users/*")
				.uri("lb://user-service"))
			.route(route -> route
				.path("/v1/articles", "/v1/articles/*")
				.uri("lb://article-service"))
			.route(route -> route
				.path("/v1/posts", "/v1/posts/*")
				.uri("lb://post-service"))
			.build();
	}
	
}