package caceresenzo.hello.gateway.configuration;

import org.springframework.cloud.gateway.filter.factory.RetryGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class RoutesConfiguration {
	
	private final RetryGatewayFilterFactory retryGatewayFilterFactory;
//	private final SpringCloudCircuitBreakerFilterFactory circuitBreakerFilterFactory;
	
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		retryGatewayFilterFactory.apply(new RetryGatewayFilterFactory.RetryConfig());
		
		return builder.routes()
				.route(route -> route
						.path("/")
						.uri("lb://index"))
				.route(route -> route
					.path("/graphql", "/graphiql")
					.uri("lb://graphql"))
				.route(route -> route
						.path("/v1/users", "/v1/users/*")
						.customize((customizer) -> {
							customizer
									// .filter(circuitBreakerFilterFactory
									// .apply(new SpringCloudCircuitBreakerFilterFactory.Config()
									// .setFallbackUri("lb://user-service")
									// .setName("user-service")))
									.filter(retryGatewayFilterFactory
											.apply(new RetryGatewayFilterFactory.RetryConfig()
													.setRetries(3)));
						})
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