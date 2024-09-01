package com.spencer.ItemApp.controller;

import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//import com.spencer.ItemApp.models.User;
//import com.spencer.ItemApp.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LoginController {
    /*@Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    */
    @GetMapping("/login")
    public String getLoginPage(HttpServletRequest request, Model model) {
        //if its the first run uncomment this code and run the login page once to create the admin account
        //User user = new User("admin", passwordEncoder.encode("password"), "ADMIN");
        //userDetailsService.save(user);
        return "login";
    }
}
