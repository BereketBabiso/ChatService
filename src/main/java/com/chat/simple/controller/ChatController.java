package com.chat.simple.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.chat.simple.dto.ChatServiceResponse;
import com.chat.simple.dto.UserMessageRequest;
import com.chat.simple.service.ChatService;
import com.chat.simple.util.ClientIpAddressIdentifier;

@RestController
@RequestMapping("/mychat")
@CrossOrigin(origins="*")
public class ChatController {

  @Autowired private ChatService chatService;

  @PostMapping("/user/message/send")
  public ChatServiceResponse sendMessage(@RequestBody UserMessageRequest userMessage,
      HttpServletRequest request, HttpServletResponse response) {
    ChatServiceResponse chatServiceResponse = null;
    
    chatServiceResponse = chatService.sendMessage(userMessage, request);
    response.setStatus(chatServiceResponse.getHttpStatus().value());
    
    return chatServiceResponse;

  }
  
  @GetMapping("/user/{globalUserId}/message/retrieve")
  public ChatServiceResponse retrieveMessages(@PathVariable String globalUserId,
      @RequestParam(name = "FromGlobalUserID", required = false) String fromUserId,
      @RequestParam(name = "MessageID", required = false) String messageId,
      HttpServletRequest request, HttpServletResponse response) {
    ChatServiceResponse chatServiceResponse = null;
    
    chatServiceResponse = chatService.retrieveMessage(globalUserId, fromUserId, messageId, request);
    response.setStatus(chatServiceResponse.getHttpStatus().value());

    return chatServiceResponse;
  }
  
  @PostMapping("/user/{globalUserId}/message/delivery")
  public ChatServiceResponse deliveryAck(@PathVariable String globalUserId,
      @RequestParam(name = "FromGlobalUserID", required = true) String fromUserId,
      @RequestParam(name = "MessageID", required = true) String messageId,
      HttpServletRequest request, HttpServletResponse response) {
    ChatServiceResponse chatServiceResponse = null;
    
    System.out.println("Client : " +request.getRemoteUser());
    System.out.println("Client IP : " + ClientIpAddressIdentifier.getClientIpAddr(request));
    System.out.println("port : " + request.getRemotePort());
    
    chatServiceResponse = chatService.messageDeliveryAck(globalUserId, fromUserId, messageId, request);
    response.setStatus(chatServiceResponse.getHttpStatus().value());
    
    return chatServiceResponse;
  }

}
