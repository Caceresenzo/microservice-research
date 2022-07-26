package caceresenzo.hello.article;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import caceresenzo.hello.common.PageResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/articles")
@RequiredArgsConstructor
public class ArticleRestController {
	
	private final ArticleRepository articleRepository;
	private final PostService postService;
	
	@GetMapping
	public PageResponse<Article> index(
		@RequestParam(name = "user", required = false) UUID userId,
		Pageable pageable,
		@RequestHeader Map<String, String> headers) {
		headers.entrySet().forEach(System.out::println);
		System.out.println(postService.findAll(PageRequest.of(1, 1), UUID.randomUUID()));
		
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