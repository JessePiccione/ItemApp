package com.spencer.ItemApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spencer.ItemApp.models.User;
import com.spencer.ItemApp.service.CustomUserDetailsService;

@Controller
public class HomeController {
	@Autowired
	private CustomUserDetailsService userDetailsService;
	@GetMapping({"/", "/home"})
	public String getHomePage(@AuthenticationPrincipal UserDetails userDetails, Model m) {
		User u = userDetailsService.getUser(userDetails.getUsername());
		m.addAttribute("username", u.getEmail());
		m.addAttribute("role", u.getRole());
		return "home_view";
	}
}
