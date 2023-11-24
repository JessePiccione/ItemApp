package com.spencer.ItemApp.controller;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
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
	public String getLoginPage(HttpServletRequest request, Model model) {
		//if its the first run uncomment this code and run the login page once to create the admin account 
		//User user = new User("admin", passwordEncoder.encode("password"), "ADMIN");
		//userDetailsService.save(user);
		return "login";
	}
}
