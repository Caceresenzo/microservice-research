package caceresenzo.hello.article;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import caceresenzo.hello.common.PageResponse;

@FeignClient("post-service")
public interface PostService {
	
	@GetMapping("/v1/posts")
	PageResponse<Object> findAll(Pageable pageable, @RequestParam UUID article);
	
}