package com.spencer.ItemApp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.spencer.ItemApp.models.RegisterDto;
import com.spencer.ItemApp.models.SendableUser;
import com.spencer.ItemApp.models.User;
import com.spencer.ItemApp.service.CustomUserDetailsService;

import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
public class AdminController {
    @Autowired
    CustomUserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping({"/user"})
    public ResponseEntity<String> getUser(@AuthenticationPrincipal UserDetails userDetails) {
        User u = userDetailsService.getUser(userDetails.getUsername());
        if (!u.isAdmin()) {
            return new ResponseEntity("{\"Error\":\"User does not have access to this feature\"}", HttpStatus.UNAUTHORIZED);
        }
        List<SendableUser> users = SendableUser.createSendableUserList(userDetailsService.getAllUsers());
        return new ResponseEntity(users, HttpStatus.OK);
    }
    @GetMapping({"/adminview"})
    public String getAdminViewPage(@AuthenticationPrincipal UserDetails userDetails, Model m) {
        User u = userDetailsService.getUser(userDetails.getUsername());
        m.addAttribute("username", u.getEmail());
        m.addAttribute("role", u.getRole());
        return "admin_view";
    }
    @GetMapping({"/create/user"})
    public String getRegistrationPage(@AuthenticationPrincipal UserDetails userDetails, Model m) {
        return "redirect:/adminview";
    }
    @PostMapping({"/create/user"})
    public ResponseEntity<String> RegisterUser(@AuthenticationPrincipal UserDetails userDetails,
                                               @RequestBody RegisterDto registerDto,
                                               Model m) {
        Map<String, String> body = new HashMap<>();
        User user = userDetailsService.getUser(userDetails.getUsername());
        if (!user.isAdmin()) {
            body.put("Error", "Requesting User Does not have access to this feature");
            return new ResponseEntity(body, HttpStatus.UNAUTHORIZED);
        }
        User newUser = new User(registerDto.getUsername(), passwordEncoder.encode(registerDto.getPassword()), registerDto.getRole());
        if (userDetailsService.hasUsername(registerDto.getUsername())) {
            body.put("Error", "User Already Exists");
            return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
        }
        userDetailsService.save(newUser);
        body.put("Success", "User Sucessfully registered");
        return new ResponseEntity(body, HttpStatus.CREATED);
    }
    @PostMapping({"/update/user"})
    public ResponseEntity<String> updateUser(@AuthenticationPrincipal UserDetails userDetails,
                                             @RequestParam long id,
                                             @RequestBody SendableUser userData) {
        User u = userDetailsService.getUser(userDetails.getUsername());
        HashMap<String, String> body = new HashMap<>();
        if (!u.isAdmin()) {
            body.put("Error", "User does not have access to this resource");
            return new ResponseEntity(body, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(userDetailsService.updateUser(id, userData), HttpStatus.OK);
    }
}
