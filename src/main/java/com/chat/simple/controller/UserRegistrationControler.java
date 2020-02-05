package com.chat.simple.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.simple.dto.ChatServiceResponse;
import com.chat.simple.dto.UserRegistrationRequest;
import com.chat.simple.service.UserRegistrationService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mychat")
public class UserRegistrationControler {
  
  @Autowired private UserRegistrationService userRegistrationService;


  @PostMapping("/create/userprofile")
  public ChatServiceResponse userRegistration(@RequestBody UserRegistrationRequest user,
      HttpServletRequest request, HttpServletResponse response) {
    ChatServiceResponse chatResponse = null;

    chatResponse = userRegistrationService.register(user, request);
    response.setStatus(chatResponse.getHttpStatus().value());

    return chatResponse;
  }

}
