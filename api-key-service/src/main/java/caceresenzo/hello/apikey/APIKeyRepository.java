package caceresenzo.hello.apikey;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface APIKeyRepository extends MongoRepository<APIKey, UUID> {

	Optional<APIKey> findByHash(String hash);
	
	Page<APIKey> findAllByUserId(UUID userId, Pageable pageable);
	
}