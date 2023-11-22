package com.spencer.ItemApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spencer.ItemApp.models.SendableUser;
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
		return userRepository.findByEmail(username).isPresent();
	}
	public User getUser(String username) {
		return userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not Found!!!"));
	}
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	public User updateUser(Long id, SendableUser newUserData){
		User u = userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User Not Found!!"));
		if(u.getId() == (newUserData.getId())) {
			if(!u.getEmail().equals(newUserData.getEmail()) && newUserData.getEmail().length() > 0) {
				u.setEmail(newUserData.getEmail());
			}
			if(!u.getRole().equals(newUserData.getRole()) && newUserData.getRole().length() > 0) {
				u.setRole(newUserData.getRole());
			}
		}
		return userRepository.save(u);
		
	}
}
