package caceresenzo.hello.auth.api.apikey;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("api-key-service")
public interface ApiKeyServiceClient {
	
	@GetMapping("/v1/api-keys/@self")
	ApiKeyDto getSelf(@RequestHeader("Authorization") String authorization);
	
}