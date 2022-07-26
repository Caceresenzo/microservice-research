package caceresenzo.hello.graphql.api.article;

import java.util.Optional;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import caceresenzo.hello.common.PageResponse;

@FeignClient("article-service")
public interface ArticleService {
	
	@RequestMapping("/v1/articles")
	public PageResponse<ArticleDto> findAll(Pageable pageable, @RequestParam(name = "user") Optional<UUID> userId);
	
	@RequestMapping("/v1/articles/{id}")
	public ArticleDto get(@PathVariable UUID id);
	
}