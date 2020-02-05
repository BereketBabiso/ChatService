package com.chat.simple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chat.simple.dto.SlackMessage;
import com.chat.simple.util.SlackUtil;

@RestController
@RequestMapping("/slack/messaging/test")
public class SlackMessagingController {
  
  @Autowired SlackUtil slackUtil;
  
  @GetMapping
  public String sendMessageToSlackChannel() {
    
    SlackMessage sm = new SlackMessage("berry", "@here this is a test message, if you get it...the you won today's chanllenge.");
    
    slackUtil.sendSlackMessage(sm);
    
    return "success";
  }

}
