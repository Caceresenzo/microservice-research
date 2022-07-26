package caceresenzo.hello.article;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, UUID> {
	
	Page<Article> findAllByUserId(UUID userId, Pageable pageable);
	
}