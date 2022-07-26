package caceresenzo.hello.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class HelloEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloEurekaApplication.class, args);
	}

}