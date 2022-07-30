package caceresenzo.hello.apikey.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.github.wimdeblauwe.errorhandlingspringbootstarter.ResponseErrorProperty;
import lombok.Getter;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiKeyNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	@ResponseErrorProperty
	private final UUID id;
	
	public ApiKeyNotFoundException(UUID id) {
		super("api key not found");
		
		this.id = id;
	}
	
}