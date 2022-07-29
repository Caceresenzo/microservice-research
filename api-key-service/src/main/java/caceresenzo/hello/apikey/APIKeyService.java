package caceresenzo.hello.apikey;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import caceresenzo.hello.common.model.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class APIKeyService {
	
	private final APIKeyRepository repository;
	
	public PageResponse<APIKeyDto> listForUserId(UUID userId, Pageable pageable) {
		Page<APIKey> apiKeys = repository.findAllByUserId(userId, pageable);
		
		return new PageResponse<>(apiKeys, APIKeyService::toDto);
	}
	
	public static APIKeyDto toDto(APIKey apiKey) {
		return new APIKeyDto()
			.setId(apiKey.getId())
			.setUserId(apiKey.getUserId())
			.setName(apiKey.getName())
			.setDescription(apiKey.getDescription())
			.setPlain(apiKey.getPlain())
			.setTruncated(apiKey.getTruncated())
			.setCreatedAt(apiKey.getCreatedAt())
			.setUpdatedAt(apiKey.getUpdatedAt())
			.setScopes(apiKey.getScopes());
	}
	
}