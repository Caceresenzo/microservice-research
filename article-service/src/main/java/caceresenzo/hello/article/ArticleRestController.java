package caceresenzo.hello.article;

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
@RequestMapping("/v1/articles")
@RequiredArgsConstructor
public class ArticleRestController {
	
	private final ArticleRepository articleRepository;
	
	@GetMapping
	public PageResponse<Article> index(
		@RequestParam(name = "user", required = false) UUID userId,
		Pageable pageable) {
		if (userId != null) {
			return new PageResponse<>(articleRepository.findAllByUserId(userId, pageable));
		}
		
		return new PageResponse<>(articleRepository.findAll(pageable));
	}
	
	@GetMapping("{id}")
	public Article index(@PathVariable UUID id) {
		return articleRepository.findById(id)
			.orElseThrow(() -> new ArticleNotFoundException(id));
	}
	
}