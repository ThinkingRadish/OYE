package com.oye.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.oye.entity.UserEntity;
import com.oye.entity.UserEntityRepository;



@Service
public class RegistrationService {
	@Autowired
	UserEntityRepository repository;
	@Autowired
	BCryptPasswordEncoder bpe;

	public String registrationLogic(String newName, String newPass){
		if(!(newName.matches("^[a-zA-Z0-9]{4,}$")) || !(newPass.matches("^[a-zA-Z0-9]{4,}$"))){
			return "badInput";
		}else{
			newPass = bpe.encode(newPass);
			UserEntity entity = new UserEntity(newName, newPass);
			try{
				repository.save(entity);
				return "registrationSuccess";
			}catch(RuntimeException e){
				return "duplicationName";
			}
		}
	}
}
