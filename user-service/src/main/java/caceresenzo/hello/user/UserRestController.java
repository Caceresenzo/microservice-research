package caceresenzo.hello.user;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import caceresenzo.hello.common.model.PageResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserRestController {
	
	private final UserRepository userRepository;
	
	@GetMapping
	public PageResponse<User> index(Pageable pageable) {
		return new PageResponse<>(userRepository.findAll(pageable));
	}
	
	@GetMapping("{id}")
	public User index(@PathVariable UUID id) {
		return userRepository.findById(id).get();
	}
	
}