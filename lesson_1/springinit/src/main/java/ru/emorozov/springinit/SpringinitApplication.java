package ru.emorozov.springinit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringinitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringinitApplication.class, args);
	}

	@RequestMapping("/**")
	public String helloWorld(){
		return "Hello world!";
	}
}
