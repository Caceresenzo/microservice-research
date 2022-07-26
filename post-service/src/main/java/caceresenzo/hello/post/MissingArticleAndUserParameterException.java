package caceresenzo.hello.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingArticleAndUserParameterException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public MissingArticleAndUserParameterException() {
		super("must provide `article` or `user`");
	}
	
}