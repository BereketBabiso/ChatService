package com.chat.simple.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserMessageResponse {
  private String fromUserId;
  private List<MessageResponse> messages;

}
