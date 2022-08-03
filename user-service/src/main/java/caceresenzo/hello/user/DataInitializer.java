package caceresenzo.hello.user;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
	
	private final UserRepository userRepository;
	private final RabbitTemplate rabbitTemplate;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		User user = userRepository.findByName("Enzo")
			.orElseGet(() -> userRepository.save(new User()
				.setId(UUID.randomUUID())
				.setName("Enzo")
				.setEmail("enzo@enzo.com")));
		
		rabbitTemplate.convertAndSend(Names.EXCHANGE, null, user);
	}
	
}