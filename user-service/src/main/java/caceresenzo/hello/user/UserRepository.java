package caceresenzo.hello.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, UUID> {
	
	Optional<User> findByName(String name);
	
}