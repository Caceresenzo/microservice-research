package caceresenzo.hello.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	@Autowired
	private ServletWebServerApplicationContext server;
	
	@GetMapping
	public ResponseEntity<String> index() {
		return ResponseEntity.ok("Hello World from port: " + server.getWebServer().getPort());
	}
	
}