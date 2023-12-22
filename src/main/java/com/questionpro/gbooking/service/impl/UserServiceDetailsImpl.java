package com.questionpro.gbooking.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.questionpro.gbooking.exception.NotFound;
import com.questionpro.gbooking.model.Account;

@Service
public class UserServiceDetailsImpl implements UserDetailsService {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username.equalsIgnoreCase("ADMIN")) {
			String password = passwordEncoder.encode("admin@123");
			Account u = new Account("ADMIN", password, "ADMIN");
			User user = new User(u.getUserName(), u.getPassword(), createAuthorize(u));
			return user;
		} else if (username.equalsIgnoreCase("USER")) {
			String password = passwordEncoder.encode("user@123");
			Account u = new Account("USER", password, "USER");
			User user = new User(u.getUserName(), u.getPassword(), createAuthorize(u));
			return user;
		} else {
			throw new NotFound("USER NOT FOUND");
		}
	}

	private Collection<? extends GrantedAuthority> createAuthorize(Account u) {
		// TODO Auto-generated method stub
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + u.getRole()));
		return authorities;
	}

}
