package ru.emorozov.ideinit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class IdeinitApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdeinitApplication.class, args);
	}

	@RequestMapping("/**")
	public String helloWorld() {
		return "Hello world!";
	}
}
