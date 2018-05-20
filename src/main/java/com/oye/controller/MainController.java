package com.oye.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.oye.service.AccountService;
import com.oye.service.GetInfoService;

@Controller
public class MainController {
	@Autowired
	GetInfoService service;
	@Autowired
	AccountService accs;

	@GetMapping("/top")
	public String toTop(Model model, Principal principal){

		model.addAttribute("twInfo", service.getTwInfoLogic());
		model.addAttribute("googleInfo", service.getGoogleInfoLogic());
		model.addAttribute("nhkInfo", service.getNHKInfoLogic());
		model.addAttribute("mainichiLink", service.getMainichiLinkLogic());
		model.addAttribute("mainichiInfo", service.getMainichiInfoLogic());

		model.addAttribute("time", service.getTime());

		model.addAttribute("isLogined", accs.isLogined(principal));
		return "public/top";
	}

	@GetMapping("/private/myEyes")
	public String toMyEyes(Model model, Principal principal){
		model.addAttribute("isLogined", accs.isLogined(principal));
		return "private/myEyes";
	}

}
