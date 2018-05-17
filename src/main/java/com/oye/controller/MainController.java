package com.oye.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oye.service.GetInfoService;

@Controller
public class MainController {
	@Autowired
	GetInfoService service;

	@GetMapping("/public/top")
	public String toTop(Model model) throws IOException {

		model.addAttribute("twInfo", service.getTwInfoLogic());
		model.addAttribute("googleInfo", service.getGoogleInfoLogic());
		model.addAttribute("nhkInfo", service.getNHKInfoLogic());
		return "public/top";
	}

	@GetMapping("/external/tw")
	public String toExternalTw(@RequestParam("keyword") String keyword) {
		try {
			return "redirect:https://twitter.com/search?q=" + URLEncoder.encode(keyword, "UTF-8") + "&src=typd&lang=ja";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "/public/error";
		}
	}

	@GetMapping("/external/nhk")
	public String toExternalNHK(@RequestParam("keyword") String keyword) {
		try {
			return "redirect:https://www2.nhk.or.jp/news/nsearch/query.cgi?col=news&charset=utf-8&qi=3&qt=" + URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "/public/error";
		}
	}


}
