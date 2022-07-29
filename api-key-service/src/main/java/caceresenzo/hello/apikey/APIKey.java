package caceresenzo.hello.apikey;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.experimental.Accessors;

@Document(collection = "api_keys")
@Data
@Accessors(chain = true)
public class APIKey {
	
	@Id
	private UUID id;
	
	@Field
	private UUID userId;

	@Field
	private String name;
	
	@Field
	private String description;
	
	@Transient
	private String plain;

	@Field
	private String hash;

	@Field
	private String truncated;

	@Field
	private LocalDateTime createdAt;

	@Field
	private LocalDateTime updatedAt;
	
	@Field
	private List<String> scopes;
	
}