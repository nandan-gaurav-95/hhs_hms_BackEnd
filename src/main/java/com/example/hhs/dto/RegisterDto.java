package com.example.hhs.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {

  private String name;
  private String email;
  private String password;
  private String gender;
  private String address;
}
