package caceresenzo.hello.graphql.api.post;

import java.util.Optional;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import caceresenzo.hello.common.PageResponse;

@FeignClient("post-service")
public interface PostService {
	
	@RequestMapping("/v1/posts")
	public PageResponse<PostDto> findAll(Pageable pageable, @RequestParam(name = "article") Optional<UUID> articleId, @RequestParam(name = "user") Optional<UUID> userId);
	
	@RequestMapping("/v1/posts/{id}")
	public PostDto get(@PathVariable UUID id);
	
}