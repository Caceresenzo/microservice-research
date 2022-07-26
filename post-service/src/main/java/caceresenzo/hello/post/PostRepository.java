package caceresenzo.hello.post;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
	
	Page<Post> findAllByArticleId(UUID articleId, Pageable pageable);
	
	Page<Post> findAllByUserId(UUID userId, Pageable pageable);
	
	Page<Post> findAllByArticleIdAndUserId(UUID articleId, UUID userId, Pageable pageable);
	
}