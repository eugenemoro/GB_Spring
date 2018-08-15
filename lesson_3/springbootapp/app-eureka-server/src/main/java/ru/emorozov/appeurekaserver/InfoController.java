package ru.emorozov.appeurekaserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class InfoController {

	@Value("${developer.name}")
	private String developerName;

	@Value("${developer.email}")
	private String email;

	@RequestMapping(value = "/info")
	public String info(){
		return "Developed by " + developerName + ". E-mail: " + email;
	}
}
