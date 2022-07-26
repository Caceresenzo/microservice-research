package caceresenzo.hello.graphql.api.article;

import java.util.Map;
import java.util.UUID;

import lombok.Data;

@Data
public class ArticleDto {
	
	private UUID id;
	private String content;
	private UUID userId;
	private Map<String, String> tags;
	
}