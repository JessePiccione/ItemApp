package com.spencer.ItemApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spencer.ItemApp.models.RegisterDto;
import com.spencer.ItemApp.models.User;
import com.spencer.ItemApp.repository.UserRepository;
import com.spencer.ItemApp.service.CustomUserDetailsService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LoginController {
	@Autowired
	private CustomUserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}
	@GetMapping("/login/register")
	public String getRegisterPage() { 
		return "register";
	}
	@PostMapping("/login/register")
	public String RegisterUser(@RequestBody RegisterDto registerDto, Model m) {
		User user  = new User(registerDto.getUsername(), passwordEncoder.encode(registerDto.getPassword()));
		if(userDetailsService.hasUsername(registerDto.getUsername())) {
			m.addAttribute("Error", "User Already Exists");
			return "login";
		}
		userDetailsService.save(user);
		m.addAttribute("Success", "User Sucessfully registered");
		return "login";
	}
}
