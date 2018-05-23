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
import com.oye.service.MyEyesService;

@Controller
public class MyEyesController {
	@Autowired
	AccountService accs;
	@Autowired
	MyEyesService mes;

	@GetMapping("/private/myEyes")
	public String toMyEyes(Model model, Principal principal) {
		model.addAttribute("isLogined", accs.isLogined(principal));
		return "private/myEyes";
	}

	@RequestMapping(value = "/private/myEyesResult", method = RequestMethod.POST)
	public String toMyEyesResult(Model model, Principal principal, @RequestParam("selectGoogle") int google,
			@RequestParam("selectTwitter") int twitter, @RequestParam("selectNHK") int nhk,
			@RequestParam("selectMainichi") int mainichi, @RequestParam("selectAsahi") int asahi,
			@RequestParam("selectYomiuri") int yomiuri, @RequestParam("selectSankei") int sankei, @RequestParam("selectNikkei") int nikkei) {

		model.addAttribute("isLogined", accs.isLogined(principal));

		if (mes.isOrderValid(google, twitter, nhk, mainichi, asahi, yomiuri, sankei, nikkei)) {

			// dbに設定保存処理
			String username = principal.getName();
			mes.registerMyEyes(username, google, twitter, nhk, mainichi, asahi, yomiuri, sankei, nikkei);

			return "private/changeMyEyesSuccess";
		} else {
			return "private/changeMyEyesFailed";
		}

	}
}
