package caceresenzo.hello.user;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.github.wimdeblauwe.errorhandlingspringbootstarter.ResponseErrorProperty;
import lombok.Getter;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	@Getter
	@ResponseErrorProperty
	private final UUID id;
	
	public UserNotFoundException(UUID userId) {
		super(String.format("no user found with id `%s`", userId));
		
		this.id = userId;
	}
	
}