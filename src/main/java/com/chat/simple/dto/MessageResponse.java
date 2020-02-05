package com.chat.simple.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageResponse {
  
  private String MessageId;
  private String message;
  private String messageCapturedTimestamp;

}
