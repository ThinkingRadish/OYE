package com.oye.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EtcController {

	@GetMapping("/public/aboutThis")
	public String toAboutThis() {
		return "public/aboutThis";
	}
}
