package caceresenzo.hello.user;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
	
	private final Environment environment;
	private final UserRepository userRepository;
	private final RabbitTemplate rabbitTemplate;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(environment.getRequiredProperty("spring.data.mongodb.database"));
		System.out.println(environment.getRequiredProperty("spring.data.mongodb.host"));
		
		User user = userRepository.findByName("Enzo")
			.orElseGet(() -> userRepository.save(new User()
				.setId(UUID.randomUUID())
				.setName("Enzo")
				.setEmail("enzo@enzo.com")));
		
		rabbitTemplate.convertAndSend(Names.EXCHANGE, null, user);
	}
	
}