package caceresenzo.hello.gateway.api.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("auth-service")
public interface AuthServiceClient {
	
	@GetMapping("/v1/authenticate")
	AuthenticatedUserDto authenticate(@RequestHeader("Authorization") String authorization);
	
}