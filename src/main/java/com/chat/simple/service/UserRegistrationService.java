package com.chat.simple.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.chat.simple.adapter.UserRegisterationAdapter;
import com.chat.simple.domain.UserProfileBO;
import com.chat.simple.dto.ChatServiceResponse;
import com.chat.simple.dto.UserRegistrationRequest;
import com.chat.simple.repository.UserProfileRepository;

@Service
public class UserRegistrationService {
  @Autowired
  private UserRegisterationAdapter registrationAdapter;
  @Autowired
  private UserProfileRepository userProfileRepository;

  public ChatServiceResponse register(UserRegistrationRequest userRequest,
      HttpServletRequest httpRequest) {
    ChatServiceResponse serviceResponse =null;
    
    UserProfileBO userBo = registrationAdapter.toUserProfileBo(userRequest);
    userProfileRepository.save(userBo);
    
    serviceResponse = registrationAdapter.toUserRegResponse(userBo, userRequest);
    serviceResponse.setHttpStatus(HttpStatus.CREATED);
    serviceResponse.setStatus("success");
    
    return serviceResponse;

  }

}
