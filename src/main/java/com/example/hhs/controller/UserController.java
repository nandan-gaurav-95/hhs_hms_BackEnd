package com.example.hhs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hhs.dto.LoginDto;
import com.example.hhs.dto.RegisterDto;
import com.example.hhs.model.User;
import com.example.hhs.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
	  
	  
	  System.err.println(registerDto);
    return new ResponseEntity<>(userService.register(registerDto), HttpStatus.OK);
  }

 
  @PostMapping("/login")
  public ResponseEntity<User> login(@RequestBody LoginDto loginDto) {
	  System.out.println("main aaya"+ loginDto);
    return (userService.login(loginDto));
  }
  
  
  @PostMapping("/forgot-password") // Use POST for password reset request
  public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
      String email = request.get("email");
      String newPassword = request.get("newPassword");
      return new ResponseEntity<>(userService.forgotPassword(email, newPassword), HttpStatus.OK);
  }
  
}
