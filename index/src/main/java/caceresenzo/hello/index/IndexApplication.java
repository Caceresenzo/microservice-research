package caceresenzo.hello.index;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IndexApplication {
	
	public static final UUID ID = UUID.randomUUID();
	
	public static void main(String[] args) {
		SpringApplication.run(IndexApplication.class, args);
	}
	
}