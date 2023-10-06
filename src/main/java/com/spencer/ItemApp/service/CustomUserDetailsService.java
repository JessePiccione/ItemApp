package com.spencer.ItemApp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spencer.ItemApp.models.User;
import com.spencer.ItemApp.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User Not Found!!!"));				
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());		
	}
	public void save(User user) {
		userRepository.save(user);
	}
	public boolean hasUsername(String username) {
		return !userRepository.findByEmail(username).isPresent();
	}

}
