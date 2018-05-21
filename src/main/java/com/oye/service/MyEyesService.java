package com.oye.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oye.entity.MyEyesEntity;
import com.oye.entity.MyEyesEntityRepository;

@Service
public class MyEyesService {
	@Autowired
	MyEyesEntityRepository meerepository;

	public boolean isOrderValid(int google, int tw, int nhk, int mai) {
		int[] order = { google, tw, nhk, mai };
		for (int i = 0; i < order.length; i++) {
			for (int j = 0; j < order.length; j++) {
				if (i == j) {
					continue;
				}
				if (order[i] == order[j] && (!(order[i] == 0))) {
					return false;
				}
			}
		}
		return true;
	}

	public void registerMyEyesLogic(String username, int google, int twitter, int nhk, int mai) {
		MyEyesEntity entity = new MyEyesEntity(username, google, twitter, nhk, mai);
		meerepository.save(entity);
	}

}
