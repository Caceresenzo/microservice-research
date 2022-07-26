package caceresenzo.hello.graphql.api.user;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import caceresenzo.hello.common.PageResponse;

@FeignClient("user-service")
public interface UserService {
	
	@RequestMapping("/v1/users")
	public PageResponse<UserDto> findAll(Pageable pageable);
	
	@RequestMapping("/v1/users/{id}")
	public UserDto get(@PathVariable UUID id);
	
}