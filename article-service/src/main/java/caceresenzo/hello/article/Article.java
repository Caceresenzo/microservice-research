package caceresenzo.hello.article;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Document
public class Article {
	
	@Id
	private UUID id;
	
	@Field
	private String content;
	
	@Field
	private UUID userId;
	
	@Field
	private Map<String, String> tags;
	
}