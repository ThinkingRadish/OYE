package com.oye.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EtcController {

	@GetMapping("/public/aboutThis")
	public String toAboutThis() {
		return "public/aboutThis";
	}

	@GetMapping("/public/aboutPrivacy")
	public String toAboutPrivacy() {
		return "public/aboutPrivacy";
	}

	@GetMapping("public/contact")
	public String toContact() {
		return "public/contact";
	}
}
