package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")	//endereço que o controller responderá 

public class HelloController {
	
	@GetMapping		//tipo de requisição
	
	public String hello() {
		return "<h1>Hello World</h1>";
	}
	

}
