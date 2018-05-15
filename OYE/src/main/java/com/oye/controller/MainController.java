package com.oye.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.oye.service.GetInfoService;
import com.oye.service.RegistrationService;

@Controller
public class MainController {
	@Autowired
	GetInfoService service;
	@Autowired
	RegistrationService rs;

	@GetMapping("/public/top")
	public String toTop(Model model) throws IOException{
		model.addAttribute("twInfo", service.getTwInfoLogic());
		return "public/top";
	}

	@GetMapping("/login/login")
	public String toLogin(){
		return "login/loginView";
	}

	@GetMapping("/login/loginFailed")
	public String toFailed(){
		return "login/loginFailed";
	}

	@GetMapping("/registration/RegisterNewUser")
	public String toCreateNewUser(){
		return "registration/RegisterNewUser";
	}

	@RequestMapping(value="/registration/RegistrationResult", method=RequestMethod.POST)
	public String toRegistrationResult(@RequestParam("newName")String newName,
	@RequestParam("newPass")String newPass){
		return "registration/" + rs.registrationLogic(newName, newPass);
	}


}
