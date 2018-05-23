package com.oye.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.oye.entity.MyEyesEntityRepository;
import com.oye.service.AccountService;
import com.oye.service.GetInfoService;
import com.oye.service.MyEyesService;

@Controller
public class MainController {
	@Autowired
	GetInfoService service;
	@Autowired
	AccountService accs;
	@Autowired
	MyEyesEntityRepository meeRepository;
	@Autowired
	MyEyesService meService;

	@GetMapping("/top")
	public String toTop(Model model, Principal principal){

		model.addAttribute("twInfo", service.getTwInfoLogic());

		model.addAttribute("googleInfo", service.getGoogleInfoLogic());

		model.addAttribute("nhkInfo", service.getNHKInfoLogic());

		model.addAttribute("mainichiLink", service.getMainichiLinkLogic());
		model.addAttribute("mainichiInfo", service.getMainichiInfoLogic());

		model.addAttribute("asahiInfo", service.getAsahiInfoLogic());
		model.addAttribute("asahiLink", service.getAsahiLinkLogic());

		model.addAttribute("yomiuriInfo", service.getYomiuriInfoLogic());
		model.addAttribute("yomiuriLink", service.getYomiuriLinkLogic());

		model.addAttribute("sankeiInfo", service.getSankeiInfoLogic());
		model.addAttribute("sankeiLink", service.getSankeiLinkLogic());

		model.addAttribute("nikkeiInfo", service.getNikkeiInfoLogic());
		model.addAttribute("nikkeiLink", service.getNikkeiLinkLogic());

		model.addAttribute("time", service.getTime());

		//ログイン ログアウトnavbarの切り替え
		model.addAttribute("isLogined", accs.isLogined(principal));

		//ログイン時レイアウトの読み込み
		if(accs.isLogined(principal)){
			ArrayList<String> list = meService.loadMyEyes(principal);

			model.addAttribute("first", list.get(0));
			model.addAttribute("second", list.get(1));
			model.addAttribute("third", list.get(2));
			model.addAttribute("forth", list.get(3));
			model.addAttribute("fifth", list.get(4));
			model.addAttribute("sixth", list.get(5));
			model.addAttribute("seventh", list.get(6));
			model.addAttribute("eighth", list.get(7));
			return "private/top";
		}else{
			return "public/top";
		}
	}



}
