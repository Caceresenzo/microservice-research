package caceresenzo.hello.user;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Document
public class User {
	
	@Id
	private UUID id;
	
	@Field
	private String name;
	
	@Field
	private String email;
	
}