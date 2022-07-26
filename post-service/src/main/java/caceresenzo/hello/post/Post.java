package caceresenzo.hello.post;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.experimental.Accessors;

@Table
@Entity
@Data
@Accessors(chain = true)
public class Post {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(updatable = false, nullable = false)
	@Type(type = "uuid-char")
	private UUID id;
	
	@Column(nullable = false)
	private String content;
	
	@Column(updatable = false, nullable = false)
	@Type(type = "uuid-char")
	private UUID articleId;
	
	@Column(updatable = false, nullable = false)
	@Type(type = "uuid-char")
	private UUID userId;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
}