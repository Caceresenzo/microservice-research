package caceresenzo.hello.post;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.github.wimdeblauwe.errorhandlingspringbootstarter.ResponseErrorProperty;
import lombok.Getter;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	@Getter
	@ResponseErrorProperty
	private final UUID id;
	
	public PostNotFoundException(UUID postId) {
		super(String.format("no post found with id `%s`", postId));
		
		this.id = postId;
	}
	
}