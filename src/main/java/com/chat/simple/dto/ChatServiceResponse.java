package com.chat.simple.dto;

import java.util.Date;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatServiceResponse {

  private String status;
  @JsonIgnore
  private HttpStatus httpStatus;
  @JsonInclude(Include.NON_NULL)
  private Date timestamp;

}
