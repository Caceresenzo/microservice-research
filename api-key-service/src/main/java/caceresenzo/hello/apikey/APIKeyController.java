package caceresenzo.hello.apikey;

import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import caceresenzo.hello.common.model.PageResponse;
import caceresenzo.hello.common.security.UserAuthenticationToken;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping(path = "/v1/api-keys", produces = MediaType.APPLICATION_JSON_VALUE)
public class APIKeyController {
	
	private final APIKeyService service;
	
	@GetMapping
	public PageResponse<APIKeyDto> list(Pageable pageable, Authentication authentication) {
		System.out.println(authentication);
		if (authentication instanceof UserAuthenticationToken token) {
			return service.listForUserId(token.getUserId(), pageable);
		}
		
		throw new OnlyUserException();
	}
	
}