package caceresenzo.hello.graphql.api.post;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class PostDto {

	private UUID id;
	private String content;
	private UUID articleId;
	private UUID userId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
}