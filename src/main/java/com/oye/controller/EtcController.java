package com.oye.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.oye.service.AccountService;

@Controller
public class EtcController {
	@Autowired
	AccountService accs;

	@GetMapping("/aboutThis")
	public String toAboutThis(Model model, Principal principal) {
		model.addAttribute("isLogined", accs.isLogined(principal));
		return "public/aboutThis";
	}
}
