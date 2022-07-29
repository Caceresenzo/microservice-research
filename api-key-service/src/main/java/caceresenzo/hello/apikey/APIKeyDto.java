package caceresenzo.hello.apikey;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class APIKeyDto {
	
	@Null
	private UUID id;
	
	@Null
	private UUID userId;
	
	@NotBlank
	@Size(min = 3, max = 60)
	private String name;
	
	@NotEmpty
	@Size(max = 300)
	private String description;
	
	@Null
	@JsonInclude(Include.NON_NULL)
	private String plain;
	
	@Null
	private String truncated;
	
	@Null
	private LocalDateTime createdAt;
	
	@Null
	private LocalDateTime updatedAt;
	
	@NotNull
	@NotEmpty
	private List<@NotBlank String> scopes;
	
}