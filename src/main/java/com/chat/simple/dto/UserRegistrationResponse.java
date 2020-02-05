package com.chat.simple.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegistrationResponse extends ChatServiceResponse{
	
	private String globalUserId;
	private UserRegistrationRequest user;

}
