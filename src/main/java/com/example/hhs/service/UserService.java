package com.example.hhs.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hhs.dto.LoginDto;
import com.example.hhs.dto.RegisterDto;
import com.example.hhs.model.User;
import com.example.hhs.repository.UserRepository;
import com.example.hhs.util.EmailUtil;
import com.example.hhs.util.OtpUtil;

import jakarta.mail.MessagingException;

@Service
public class UserService {

  @Autowired
  private OtpUtil otpUtil;
  @Autowired
  private EmailUtil emailUtil;
  @Autowired
  private UserRepository userRepository;

  public String register(RegisterDto registerDto) {
	    User existingUser = userRepository.findByEmail(registerDto.getEmail());
	    if (existingUser != null) {
	      return "User with this email already exists";
	    }
	    
	    User user = new User();
	    user.setEmail(registerDto.getEmail());
	    user.setPassword(registerDto.getPassword());
	    user.setAddress(registerDto.getAddress());
	    user.setName(registerDto.getName());
	    user.setGender(registerDto.getGender());
	    
	    user.setActive(true); // Activate the user immediately
	    userRepository.save(user);
	    
	    return "User registration successful";
	  }

 

  public String login(LoginDto loginDto) {
	    User user = userRepository.findByEmail(loginDto.getEmail());
	    if (user == null || !user.isActive()) {
	      return "Invalid email or account not activated";
	    }
	    
	    if (!loginDto.getPassword().equals(user.getPassword())) {
	      return "Incorrect password";
	    }

	    return "Login successful";
	  }

  
  
  public String forgotPassword(String email, String newPassword) {
	    User user = userRepository.findByEmail(email);
	    if (user == null) {
	        throw new RuntimeException("User not found with this email: " + email);
	    }

	    // Update the user's password with the new password
	    user.setPassword(newPassword);

	    // Save the updated user information
	    userRepository.save(user);

	    return "Password reset successfully";
	}
}
