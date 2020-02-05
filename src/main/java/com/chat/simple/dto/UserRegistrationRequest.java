package com.chat.simple.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {

  private String firstName;
  private String lastName;
  private String phone;
  private String email;


}
