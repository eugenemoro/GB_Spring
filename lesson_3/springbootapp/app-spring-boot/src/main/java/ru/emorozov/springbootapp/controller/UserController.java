package ru.emorozov.springbootapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.emorozov.springbootapp.entity.User;
import ru.emorozov.springbootapp.repository.UserRepository;
import java.util.Map;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = {"/user-list"}, method = RequestMethod.GET)
	public String userList(Map<String, Object> model) {
		model.put("users", userRepository.findAll());
		return "user-list";
	}

	@RequestMapping(value = {"/user-create"}, method = RequestMethod.GET)
	public String userCreate() {
		userRepository.saveAndFlush(new User());
		return "redirect:/user-list";
	}

	@RequestMapping(value = {"/user-remove"}, method = RequestMethod.GET)
	public String userRemove(@RequestParam("id") String userId) {
		userRepository.deleteById(userId);
		return "redirect:/user-list";
	}

	@RequestMapping(value = {"/user-edit"}, method = RequestMethod.GET)
	public String userEdit(@RequestParam("id") String userId, Map<String, Object> model) {
		final User user = userRepository.findById(userId).get();
		model.put("user", user);
		return "user-edit";
	}

	@RequestMapping(value = {"/user-save"}, method = RequestMethod.POST)
	public String userSave(@ModelAttribute("user") User user) {
		userRepository.saveAndFlush(user);
		return "redirect:/user-list";
	}
}
