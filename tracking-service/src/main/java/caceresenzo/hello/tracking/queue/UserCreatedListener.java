package caceresenzo.hello.tracking.queue;

import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import caceresenzo.hello.tracking.configuration.Names;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserCreatedListener {
	
	@RabbitListener(queues = {
		Names.QUEUE
	})
	public void onUserCreated(UserDto user) {
		log.info("onUserCreated({})", user);
	}
	
	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class UserDto {
		
		private UUID id;
		private String name;
		private String email;
		
	}
	
}