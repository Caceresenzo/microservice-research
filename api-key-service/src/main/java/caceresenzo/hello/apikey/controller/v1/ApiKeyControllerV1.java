package caceresenzo.hello.apikey.controller.v1;

import java.util.Objects;
import java.util.UUID;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import caceresenzo.hello.apikey.dto.ApiKeyDto;
import caceresenzo.hello.apikey.exception.ApiKeyNotFoundException;
import caceresenzo.hello.apikey.permission.CanRead;
import caceresenzo.hello.apikey.permission.CanWrite;
import caceresenzo.hello.apikey.security.token.RawApiKeyAuthenticationToken;
import caceresenzo.hello.apikey.service.ApiKeyService;
import caceresenzo.hello.common.model.PageResponse;
import caceresenzo.hello.common.security.token.UserAuthenticationToken;
import caceresenzo.hello.common.web.exception.ForbiddenException;
import caceresenzo.hello.common.web.exception.OnlyUserException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping(path = "/v1/api-keys", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "api-key", description = "API-Key related operations.")
public class ApiKeyControllerV1 {
	
	private final ApiKeyService service;
	
	@CanRead
	@GetMapping
	@Operation(summary = "List user's API-Keys.")
	public PageResponse<ApiKeyDto> list(@ParameterObject Pageable pageable, Authentication authentication) {
		if (authentication instanceof UserAuthenticationToken token) {
			return service.listForUserId(token.getUserId(), pageable);
		}
		
		throw new OnlyUserException();
	}
	
	@CanWrite
	@PutMapping
	@Operation(summary = "Create an API-Keys.")
	public ApiKeyDto create(@Validated @RequestBody ApiKeyDto body, Authentication authentication) {
		if (authentication instanceof UserAuthenticationToken token) {
			return service.create(body, token.getUserId());
		}
		
		throw new OnlyUserException();
	}
	
	@CanWrite
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping
	@Operation(summary = "Delete all's user API-Keys.")
	public void deleteAll(Authentication authentication) {
		if (authentication instanceof UserAuthenticationToken token) {
			service.deleteAllByUserId(token.getUserId());
		} else {
			throw new OnlyUserException();
		}
	}
	
	@PreAuthorize("authenticated")
	@GetMapping("@self")
	public ApiKeyDto showSelf(Authentication authentication) {
		if (authentication instanceof RawApiKeyAuthenticationToken token) {
			return token.getApiKey();
		}
		
		throw new ForbiddenException();
	}
	
	@CanRead
	@GetMapping("{id}")
	@Operation(summary = "Show an API-Key.")
	public ApiKeyDto show(@PathVariable UUID id, Authentication authentication) {
		if (authentication instanceof UserAuthenticationToken token) {
			return getApiKey(id, token);
		}
		
		throw new OnlyUserException();
	}
	
	@CanWrite
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	@Operation(summary = "Delete an API-Key.")
	public void delete(@PathVariable UUID id, Authentication authentication) {
		if (authentication instanceof UserAuthenticationToken token) {
			ApiKeyDto apiKey = getApiKey(id, token);
			
			service.delete(apiKey.getId());
		} else {
			throw new OnlyUserException();
		}
	}
	
	public ApiKeyDto getApiKey(UUID id, UserAuthenticationToken authentication) {
		ApiKeyDto apiKey = service.findById(id).orElseThrow(() -> new ApiKeyNotFoundException(id));
		
		if (!Objects.equals(authentication.getUserId(), apiKey.getUserId())) {
			throw new ForbiddenException();
		}
		
		return apiKey;
	}
	
}