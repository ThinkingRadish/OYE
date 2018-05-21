package com.oye.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oye.service.AccountService;

@Controller
public class EtcController {
	@Autowired
	AccountService accs;

	@GetMapping("/public/aboutThis")
	public String toAboutThis(Model model, Principal principal) {
		model.addAttribute("isLogined", accs.isLogined(principal));
		return "public/aboutThis";
	}

	@GetMapping("/external/tw")
	public String toExternalTw(@RequestParam("keyword") String keyword) {
		try {
			return "redirect:https://twitter.com/search?q=" + URLEncoder.encode(keyword, "UTF-8") + "&src=typd&lang=ja";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "/error";
		}
	}

	@GetMapping("/external/google")
	public String toExternalGoogle(@RequestParam("keyword") String keyword) {
		try {
			return "redirect:https://www.google.co.jp/search?q=" + URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "/error";
		}
	}

	@GetMapping("/external/nhk")
	public String toExternalNHK(@RequestParam("keyword") String keyword) {
		try {
			return "redirect:https://www2.nhk.or.jp/news/nsearch/query.cgi?col=news&charset=utf-8&qi=3&qt="
					+ URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "/error";
		}
	}

	@GetMapping("/external/mainichi")
	public String toExternalMainichi(@RequestParam("keyword") String keyword) {
		return "redirect:" + keyword;
	}

	@GetMapping("/external/asahi")
	public String toExternalAsahi(@RequestParam("keyword") String keyword){
		return "redirect:" + keyword;
	}
}
