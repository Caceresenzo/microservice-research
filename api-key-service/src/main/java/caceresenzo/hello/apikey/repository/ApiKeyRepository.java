package caceresenzo.hello.apikey.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import caceresenzo.hello.apikey.entity.ApiKey;

public interface ApiKeyRepository extends MongoRepository<ApiKey, UUID> {

	Optional<ApiKey> findByHash(String hash);
	
	Page<ApiKey> findAllByUserId(UUID userId, Pageable pageable);

	void deleteAllByUserId(UUID userId);
	
}