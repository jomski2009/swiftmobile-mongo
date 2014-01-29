package org.imanmobile.sms.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
	@RequestMapping()
	public ResponseEntity<String> home() {
		return new ResponseEntity<String>("Welcome to Iman Mobile",
				HttpStatus.OK);
	}
}
