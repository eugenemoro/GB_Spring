package ru.emorozov.springbootapp.controller;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class WelcomeController {

	private String message;

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		final ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hello World!'.concat(' SpEL used!')");
		this.message = (String) exp.getValue();
		model.put("message", message);
		return "welcome";
	}
}