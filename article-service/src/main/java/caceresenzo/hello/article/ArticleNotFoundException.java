package caceresenzo.hello.article;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.github.wimdeblauwe.errorhandlingspringbootstarter.ResponseErrorProperty;
import lombok.Getter;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArticleNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	@Getter
	@ResponseErrorProperty
	private final UUID id;
	
	public ArticleNotFoundException(UUID articleId) {
		super(String.format("no article found with id `%s`", articleId));
		
		this.id = articleId;
	}
	
}