package com.skillstorm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.dtos.RegisterRequest;
import com.skillstorm.dtos.RegisterResponse;
import com.skillstorm.models.AppUser;
import com.skillstorm.services.UserService;

@RestController
public class UserController {

	// Login
	// Not needed
//	@PostMapping("/login")
//	public void login() {
//		
//	}
	
	@Autowired
	private UserService userService;
	
	// Register
	@PostMapping("/register")
	public RegisterResponse register(@RequestBody RegisterRequest authDetails) {
		AppUser user = userService.register(authDetails.getUsername(), authDetails.getPassword());
		
		return new RegisterResponse(user.getUsername(), user.getRole());
	}
}
