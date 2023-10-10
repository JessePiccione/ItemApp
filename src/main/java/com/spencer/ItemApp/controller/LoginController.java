package com.spencer.ItemApp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;

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
	public ResponseEntity<String> RegisterUser(@RequestBody RegisterDto registerDto, Model m) {
		Map<String, String> body = new HashMap<>();
		User user  = new User(registerDto.getUsername(), passwordEncoder.encode(registerDto.getPassword()));
		if(userDetailsService.hasUsername(registerDto.getUsername())) {
			body.put("Error", "User Already Exists");
			return new ResponseEntity(body,HttpStatus.BAD_REQUEST);
		}
		userDetailsService.save(user);
		body.put("Success", "User Sucessfully registered");
		return  new ResponseEntity(body, HttpStatus.CREATED);
	}
}
