package caceresenzo.hello.graphql;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;

@Component
public class OptionalFeignFormatterRegistrar implements FeignFormatterRegistrar {
	
	@Override
	public void registerFormatters(FormatterRegistry registry) {
		registry.addConverter(Optional.class, String.class, (optional) -> {
			if (optional.isPresent()) {
				return optional.get().toString();
			} else {
				return null;
			}
		});
	}
}