package com.spencer.ItemApp.controller;

import com.spencer.ItemApp.models.FileUpload;
import com.spencer.ItemApp.service.FileUploadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spencer.ItemApp.models.User;
import com.spencer.ItemApp.service.CustomUserDetailsService;

import java.util.List;

@Controller
public class HomeController {
	@Autowired
	private CustomUserDetailsService userDetailsService;
	@Autowired
	private FileUploadService fileUploadService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@GetMapping({"/", "/home"})
	public String getHomePage(@AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request, Model m) {
		User u = userDetailsService.getUser(userDetails.getUsername());
		List<FileUpload> fileEntries = fileUploadService.findAll();
		if(fileEntries.isEmpty()) fileEntries.add(new FileUpload("dummyfilename.txt", "2023-11-29"));
		List<User> users = userDetailsService.getAllUsers();
		if(users.isEmpty()) users.add(new User("DummyUser",passwordEncoder.encode("password"),"USER"));
		m.addAttribute("username", u.getEmail());
		m.addAttribute("role", u.getRole());
		m.addAttribute("totalFile", fileEntries.size());
		m.addAttribute("lastFile", fileEntries.get(fileEntries.size()-1).getFileName());
		m.addAttribute("totalUsers", users.size());
		m.addAttribute("lastUser", users.get(users.size()-1).getEmail());
		return "home_view";
	}
}
