package caceresenzo.hello.apikey.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import caceresenzo.hello.apikey.dto.ApiKeyDto;
import caceresenzo.hello.apikey.entity.ApiKey;
import caceresenzo.hello.apikey.repository.ApiKeyRepository;
import caceresenzo.hello.common.model.PageResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ApiKeyService {
	
	private final ApiKeyRepository repository;
	
	public Optional<ApiKeyDto> findById(UUID id) {
		return repository.findById(id).map(ApiKeyService::toDto);
	}

	public Optional<ApiKeyDto> findByPlain(String plain) {
		return repository.findByHash(hash(plain)).map(ApiKeyService::toDto);
	}
	
	public PageResponse<ApiKeyDto> listForUserId(UUID userId, Pageable pageable) {
		Page<ApiKey> apiKeys = repository.findAllByUserId(userId, pageable);
		
		return new PageResponse<>(apiKeys, ApiKeyService::toDto);
	}
	
	public ApiKeyDto create(ApiKeyDto body, UUID userId) {
		String plain = Long.toHexString(Double.doubleToLongBits(Math.random()));
		
		ApiKey apiKey = repository.save(new ApiKey()
			.setId(UUID.randomUUID())
			.setUserId(userId)
			.setName(body.getName())
			.setDescription(body.getDescription())
			.setPlain(plain)
			.setHash(hash(plain))
			.setTruncated(truncate(plain))
			.setCreatedAt(LocalDateTime.now())
			.setUpdatedAt(LocalDateTime.now())
			.setScopes(body.getScopes()));
		
		return toDto(apiKey);
	}
	
	public String hash(String plain) {
		return DigestUtils.sha256Hex(plain);
	}
	
	public static String truncate(String plain) {
		return plain.substring(0, 4) + "...";
	}
	
	public static ApiKeyDto toDto(ApiKey apiKey) {
		return new ApiKeyDto()
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
	
	public void delete(UUID id) {
		repository.delete(new ApiKey().setId(id));
	}

	public void deleteAllByUserId(UUID userId) {
		repository.deleteAllByUserId(userId);
	}
	
}