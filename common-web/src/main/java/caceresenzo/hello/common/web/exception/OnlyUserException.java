package caceresenzo.hello.common.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class OnlyUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public OnlyUserException() {
		super("only a user can do this");
	}
	
}