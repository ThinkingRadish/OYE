package com.oye.service;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oye.entity.MyEyesEntity;
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

	public ArrayList<String> loadMyEyes(Principal principal) {
		MyEyesEntity entity = meeRepository.findByUsername(principal.getName());
		int google = entity.getGoogle();
		int twitter = entity.getTwitter();
		int nhk = entity.getNhk();
		int mainichi = entity.getMainichi();

		int[] orderI = { google, twitter, nhk, mainichi };
		String[] orderS = {"frag_google", "frag_twitter", "frag_nhk", "frag_mainichi", "frag_dummy" };

		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < orderS.length; i++) {
			for (int j = 0; j < orderI.length; j++) {

				if (orderI[j] == i) {
					if(i == 0){
						list.add(orderS[orderS.length-1]);
						continue;
					}
					switch(j){
					case 0: list.add(orderS[0]);break;
					case 1: list.add(orderS[1]);break;
					case 2: list.add(orderS[2]);break;
					case 3: list.add(orderS[3]);break;
					case 4: list.add(orderS[4]);break;
					}
				}
			}
		}
		return list;
	}

}
