package es.golemdr.libreriaweb.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BasicController {
	

	
	@GetMapping("/check-app")
	public @ResponseBody String checkApp() {

		return "STATUS: UP";		
	}
	
}
