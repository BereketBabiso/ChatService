package com.chat.simple.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RetrieveChatMessageResponse extends ChatServiceResponse {
  
  private List<UserMessageResponse> chatMessages;

}
