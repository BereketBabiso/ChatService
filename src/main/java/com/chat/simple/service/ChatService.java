package com.chat.simple.service;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.chat.simple.adapter.ChatServiceAdapter;
import com.chat.simple.domain.MessageBO;
import com.chat.simple.dto.ChatServiceResponse;
import com.chat.simple.dto.RetrieveChatMessageResponse;
import com.chat.simple.dto.UserMessageRequest;
import com.chat.simple.dto.UserMessageResponse;
import com.chat.simple.repository.MessageRepository;
import com.chat.simple.util.ChatServiceUtility;

@Service
public class ChatService {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ChatService.class);

  @Autowired
  private ChatServiceAdapter chatServiceAdapter;
  @Autowired
  private MessageRepository messageRepository;
  public ChatServiceResponse sendMessage(UserMessageRequest userMessage,
      HttpServletRequest request) {
    ChatServiceResponse response = null;

    MessageBO msgBO = chatServiceAdapter.toMessageBo(userMessage);
    messageRepository.save(msgBO);

    response = new ChatServiceResponse();
    response.setStatus("Success");
    response.setHttpStatus(HttpStatus.OK);


    return response;
  }
  
  public ChatServiceResponse retrieveMessage(String userUuid, String fromUserId, String messageId,
      HttpServletRequest request) {
    ChatServiceResponse response = null;

    // Validations happen here

    if (ChatServiceUtility.isAllStringsEmpty(fromUserId, messageId)) {
      LOGGER.info("processing messages sent to the user using the globalId of the user " + userUuid);
      List<MessageBO> messageForUser = messageRepository
          .findByToUserGuidAndSentTimestampNullOrderByCapturedTimestampDesc(userUuid);
      if(null!=messageForUser && !messageForUser.isEmpty()) {
        List<UserMessageResponse> userMsgResp = chatServiceAdapter.transformMessageBos(messageForUser);
        RetrieveChatMessageResponse retieveMessageResp = new RetrieveChatMessageResponse();
        retieveMessageResp.setChatMessages(userMsgResp);
        retieveMessageResp.setStatus("Success");
        retieveMessageResp.setHttpStatus(HttpStatus.OK);
        response = retieveMessageResp;
        
        //update the sentTimestamp of the msgs
        messageRepository.saveAll(messageForUser);
      } else {
        response = new ChatServiceResponse();
        response.setStatus("No new Message found");
        response.setHttpStatus(HttpStatus.OK);
      }
    }

    return response;
  }
  
  public ChatServiceResponse messageDeliveryAck(String userUuid, String fromUserId,
      String messageId, HttpServletRequest request) {
    ChatServiceResponse response = null;

    // basic validations must be put in place here

    MessageBO msgBo =
        messageRepository.findByToUserGuidAndFromUserGuidAndMessageKeyAndDelivedTimestampNull(
            userUuid, fromUserId, messageId);
    if (null != msgBo) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
      msgBo.setDelivedTimestamp(ChatServiceUtility.formatCurrentTimeStamp(sdf));
      messageRepository.save(msgBo);
    }

    response = new ChatServiceResponse();
    response.setStatus("Success");
    response.setHttpStatus(HttpStatus.OK);

    return response;
  }
}
