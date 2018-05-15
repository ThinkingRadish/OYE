package com.oye.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserEntityDetailsService implements UserDetailsService {
	@Autowired
	UserEntityRepository uer;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username == null || "".equals(username)) {
            throw new UsernameNotFoundException("Username is empty");
        }

        UserEntity ue = uer.findByUsername(username);
        if (ue == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }



        UserEntityDetails user = new UserEntityDetails(ue);

        return user;
	}

}
