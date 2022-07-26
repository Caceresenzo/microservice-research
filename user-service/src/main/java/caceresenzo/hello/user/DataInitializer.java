package caceresenzo.hello.user;

import java.util.UUID;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
	
	private final UserRepository userRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		userRepository.findByName("Enzo")
				.orElseGet(() -> userRepository.save(new User()
						.setId(UUID.randomUUID())
						.setName("Enzo")
						.setEmail("enzo@enzo.com")));
	}
	
}