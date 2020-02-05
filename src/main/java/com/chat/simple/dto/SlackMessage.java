package com.chat.simple.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SlackMessage implements Serializable {
  
  private String user;
  private String text;

}
