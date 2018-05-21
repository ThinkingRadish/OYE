package com.oye.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oye.entity.MyEyesEntityRepository;

@Service
public class AccountService {
	@Autowired
	MyEyesEntityRepository meeRepository;

	public boolean isLogined(Principal principal) {
		try {
			principal.getName();
			return true;
		} catch (NullPointerException e) {
			return false;
		}
	}



}
