package com.chat.simple.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.chat.simple.domain.UserProfileBO;
import com.chat.simple.dto.UserRegistrationRequest;
import com.chat.simple.dto.UserRegistrationResponse;
import com.chat.simple.util.UuidGeneratory;

@Component
public class UserRegisterationAdapter {
  @Autowired
  private UuidGeneratory uuidGeneratory;

  public UserProfileBO toUserProfileBo(UserRegistrationRequest userRequest) {
    UserProfileBO userProfileBO = new UserProfileBO();
    userProfileBO.setUserId(uuidGeneratory.generateUuid(
        !userRequest.getPhone().isEmpty() ? userRequest.getPhone() : userRequest.getEmail()));
    userProfileBO.setFirstName(userRequest.getFirstName());
    userProfileBO.setLastName(userRequest.getLastName());
    userProfileBO.setPhone(userRequest.getPhone());
    userProfileBO.setEmail(userRequest.getEmail());

    return userProfileBO;
  }

  public UserRegistrationResponse toUserRegResponse(UserProfileBO userProfileBO,
      UserRegistrationRequest userRequest) {
    UserRegistrationResponse userRegResponse = new UserRegistrationResponse();
    userRegResponse.setGlobalUserId(userProfileBO.getUserId());
    userRegResponse.setUser(userRequest);

    return userRegResponse;
  }

}
