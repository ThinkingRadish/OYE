package com.oye.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.oye.entity.UserEntityDetailsService;

//@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserEntityDetailsService ueds;

	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.formLogin().loginPage("/login/login").defaultSuccessUrl("/top").failureUrl("/login/loginFailed").permitAll();
		http.logout().logoutSuccessUrl("/top").permitAll();
		http.authorizeRequests().antMatchers("/css/**", "/img/**", "/js/**", "/**", "/login/**", "/registration/**").permitAll().anyRequest().authenticated();
	}
}
