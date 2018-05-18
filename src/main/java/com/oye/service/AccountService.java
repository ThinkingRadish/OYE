package com.oye.service;

import java.security.Principal;

import org.springframework.stereotype.Service;

@Service
public class AccountService {

	public boolean isLogined(Principal principal){
		try{
			principal.getName();
			return true;
		}catch(NullPointerException e){
			return false;
		}
	}

}
