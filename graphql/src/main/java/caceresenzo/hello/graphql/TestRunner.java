package caceresenzo.hello.graphql;

import java.util.List;

import javax.servlet.Filter;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TestRunner implements ApplicationRunner {
	
	private final List<Filter> filters;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		filters.forEach(System.out::println);
	}
	
}