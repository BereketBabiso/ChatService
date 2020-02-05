package com.chat.simple.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserMessageRequest {
  
  private String fromGuid;
  private String toGuid;
  private UserMessage message;

}
