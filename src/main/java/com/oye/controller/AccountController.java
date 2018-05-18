package com.oye.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.oye.service.AccountService;
import com.oye.service.RegistrationService;

@Controller
public class AccountController {

	@Autowired
	RegistrationService rs;
	@Autowired
	AccountService accs;

	@GetMapping("/login/login")
	public String toLogin(Model model, Principal principal){
		model.addAttribute("isLogined", accs.isLogined(principal));
		return "login/loginView";
	}

	@GetMapping("/login/loginFailed")
	public String toFailed(Model model, Principal principal){
		model.addAttribute("isLogined", accs.isLogined(principal));
		return "login/loginFailed";
	}

	@GetMapping("/registration/RegisterNewUser")
	public String toCreateNewUser(Model model, Principal principal){
		model.addAttribute("isLogined", accs.isLogined(principal));
		return "registration/RegisterNewUser";
	}

	@RequestMapping(value="/registration/RegistrationResult", method=RequestMethod.POST)
	public String toRegistrationResult(@RequestParam("newName")String newName,
	@RequestParam("newPass")String newPass, Model model, Principal principal){
		model.addAttribute("isLogined", accs.isLogined(principal));
		return "registration/" + rs.registrationLogic(newName, newPass);
	}

}
