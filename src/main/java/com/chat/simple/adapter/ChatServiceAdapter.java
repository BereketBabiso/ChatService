package com.chat.simple.adapter;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.chat.simple.domain.MessageBO;
import com.chat.simple.dto.MessageResponse;
import com.chat.simple.dto.UserMessageRequest;
import com.chat.simple.dto.UserMessageResponse;
import com.chat.simple.util.ChatServiceUtility;

@Component
public class ChatServiceAdapter {
  
  
  public MessageBO toMessageBo(UserMessageRequest userMessage) {
    MessageBO messageBO = new MessageBO();
    messageBO.setMessageKey(userMessage.getMessage().getMessageId());
    messageBO.setFromUserGuid(userMessage.getFromGuid());
    messageBO.setToUserGuid(userMessage.getToGuid());
    messageBO.setMessage(userMessage.getMessage().getMessage());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS");
    messageBO.setCapturedTimestamp(ChatServiceUtility.formatCurrentTimeStamp(sdf));
    
    return messageBO;
  }
  
  public List<UserMessageResponse> transformMessageBos(List<MessageBO> msgBos) {
    List<UserMessageResponse> userMsgResp = new ArrayList<>();
    Map<String, List<MessageResponse>> sendingUserMsgs = new HashMap<>();
    msgBos.forEach(msgBo -> {
      sendingUserMsgs.computeIfPresent(msgBo.getFromUserGuid(),
          (key, val) -> addToList(val, msgBo));
      sendingUserMsgs.computeIfAbsent(msgBo.getFromUserGuid(),
          key -> addToList(null, msgBo));
      msgBo.setSentTimestamp(Date.valueOf(LocalDate.now()));
    });
    sendingUserMsgs.keySet().forEach(fromUserId -> {
      UserMessageResponse msgsFromUser = new UserMessageResponse();
      msgsFromUser.setFromUserId(fromUserId);
      msgsFromUser.setMessages(sendingUserMsgs.get(fromUserId));
      userMsgResp.add(msgsFromUser);
    });
    return userMsgResp;
  }

  private List<MessageResponse> addToList(List<MessageResponse> val, MessageBO msgBo) {
    if(null!=val) val.add(toMessageResponse(msgBo));
    else {
      val= new ArrayList<MessageResponse>();
      val.add(toMessageResponse(msgBo));
    }
    return val;
  }

  public MessageResponse toMessageResponse(MessageBO msgBo) {
    MessageResponse messageResponse = new MessageResponse();
    messageResponse.setMessageId(msgBo.getMessageKey());
    messageResponse.setMessage(msgBo.getMessage());
    messageResponse.setMessageCapturedTimestamp(msgBo.getCapturedTimestamp().toString());

    return messageResponse;
  }

}
