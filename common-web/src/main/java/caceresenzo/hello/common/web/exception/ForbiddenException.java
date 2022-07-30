package caceresenzo.hello.common.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ForbiddenException() {
		super("you don't have permission to do this");
	}
	
}