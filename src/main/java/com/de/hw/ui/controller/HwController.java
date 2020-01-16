package com.de.hw.ui.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author demirtaserdem@gmail.com
 * 16 Oca 2020
 */

@RestController
@RequestMapping("/")
public class HwController {

	@GetMapping
	public String helloWorld() {
		String responseText;
		responseText = "Hello World";
		return responseText;
	}
}
