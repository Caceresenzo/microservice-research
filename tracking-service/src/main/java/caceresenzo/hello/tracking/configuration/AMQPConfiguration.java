package caceresenzo.hello.tracking.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration(proxyBeanMethods = false)
public class AMQPConfiguration {
	
	@Bean
	FanoutExchange userCreatedExchange() {
		return new FanoutExchange(Names.EXCHANGE);
	}
	
	@Bean
	Queue userCreatedQueue() {
		return new Queue(Names.QUEUE, true);
	}
	
	@Bean
	Binding firstBinding(Queue userCreatedQueue, FanoutExchange userCreatedExchange) {
		return BindingBuilder
			.bind(userCreatedQueue)
			.to(userCreatedExchange);
	}
	
	@Primary
	@Bean
	Jackson2JsonMessageConverter producerJackson2MessageConverter(ObjectMapper objectMapper) {
		return new Jackson2JsonMessageConverter(objectMapper);
	}
	
}