package caceresenzo.hello.graphql;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import caceresenzo.hello.graphql.api.article.ArticleDto;
import caceresenzo.hello.graphql.api.article.ArticleService;
import caceresenzo.hello.graphql.api.post.PostDto;
import caceresenzo.hello.graphql.api.post.PostService;
import caceresenzo.hello.graphql.api.user.UserDto;
import caceresenzo.hello.graphql.api.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
class MyController {
	
	private final UserService userService;
	private final PostService postService;
	private final ArticleService articleService;

    @QueryMapping
    List<UserDto> users() {
        return userService.findAll(PageRequest.of(0, 10)).getContent();
    }
    
    @QueryMapping
    List<ArticleDto> articles() {
    	return articleService.findAll(PageRequest.of(0, 10), Optional.empty()).getContent();
    }
    
    @QueryMapping
    List<PostDto> postsByUserId(@Argument UUID userId) {
    	return postService.findAll(PageRequest.of(0, 10), Optional.empty(), Optional.of(userId)).getContent();
    }
    
    @QueryMapping
    List<ArticleDto> articlesByUserId(@Argument UUID userId) {
    	return articleService.findAll(PageRequest.of(0, 10), Optional.of(userId)).getContent();
    }
    
    @SchemaMapping(typeName = "Post")
    UserDto user(PostDto post) {
    	log.info("MyController.user({})", post);
    	
    	return userService.get(post.getUserId());
    }

    @SchemaMapping(typeName = "Post")
    ArticleDto article(PostDto post) {
    	return articleService.get(post.getArticleId());
    }

    @SchemaMapping(typeName = "Article")
    List<PostDto> posts(ArticleDto article) {
    	return postService.findAll(PageRequest.of(0, 10), Optional.of(article.getId()), Optional.empty()).getContent();
    }

    @SchemaMapping(typeName = "Article")
    UserDto user(ArticleDto article) {
    	return userService.get(article.getUserId());
    }
    
}