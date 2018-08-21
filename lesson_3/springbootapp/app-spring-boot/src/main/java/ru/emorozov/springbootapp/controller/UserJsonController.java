package ru.emorozov.springbootapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.emorozov.springbootapp.entity.User;
import ru.emorozov.springbootapp.repository.UserRepository;

@RestController
public class UserJsonController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/api/user/info")
	public User userInfo(@RequestParam(name="id", required=false, defaultValue="null") String id) {
		if (id.isEmpty()) return new User();
		return userRepository.getOne(id);
	}
}
