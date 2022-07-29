package caceresenzo.hello.post;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import caceresenzo.hello.common.model.PageResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/posts")
@RequiredArgsConstructor
public class PostRestController {
	
	private final PostRepository postRepository;
	
	@GetMapping
	public PageResponse<Post> index(
		@RequestParam(name = "article", required = false) UUID articleId,
		@RequestParam(name = "user", required = false) UUID userId,
		Pageable pageable) {
		if (articleId == null && userId == null) {
			throw new MissingArticleAndUserParameterException();
		}
		
		if (articleId != null && userId != null) {
			return new PageResponse<>(postRepository.findAllByArticleIdAndUserId(articleId, userId, pageable));
		}
		
		if (articleId != null) {
			return new PageResponse<>(postRepository.findAllByArticleId(articleId, pageable));
		}
		
		if (userId != null) {
			return new PageResponse<>(postRepository.findAllByUserId(userId, pageable));
		}
		
		throw new IllegalStateException();
	}
	
	@GetMapping("{id}")
	public Post index(@PathVariable UUID id) {
		return postRepository.findById(id)
			.orElseThrow(() -> new PostNotFoundException(id));
	}
	
}