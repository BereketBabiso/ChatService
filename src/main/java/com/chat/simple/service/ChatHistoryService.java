package com.chat.simple.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.chat.simple.domain.HistoryMessageBO;
import com.chat.simple.domain.MessageBO;
import com.chat.simple.repository.HistoryMessageRepository;
import com.chat.simple.repository.MessageRepository;

@Service
public class ChatHistoryService {

  @Autowired
  private MessageRepository messageRepository;
  @Autowired
  private HistoryMessageRepository historyMessageRepository;
  @Value("${application.envis.batch.size}")
  private int batchSize;
  
  public void clearAlreadyDeliveredMessages() {
    Pageable pageable = PageRequest.of(0, batchSize);
   
    clearDeliveredMeaasges(pageable);
  }

  public void clearDeliveredMeaasges(Pageable pageable) {

    System.out.println("pageable request size : " + pageable);
    while (null != pageable) {
      Pageable nextPageable = null;
      Page<MessageBO> messagePage = messageRepository.findAllByDelivedTimestampNotNull(pageable);
      if (messagePage != null && !messagePage.isEmpty()) {
        List<MessageBO> messagesToClear = messagePage.getContent();
        List<MessageBO> msgToClear = getMessagesToClear(messagesToClear);
        messageRepository.deleteAll(msgToClear);

        List<HistoryMessageBO> MessagesToHistory = constructHistoryMessage(msgToClear);
        historyMessageRepository.saveAll(MessagesToHistory);

        nextPageable = messagePage.nextPageable();
        System.out.println("next pageable " + nextPageable);

        messagesToClear = null;
        msgToClear = null;
        MessagesToHistory = null;
      }
      pageable = nextPageable;
    }
  }

  protected List< MessageBO> getMessagesToClear(List<MessageBO> messagesToClear) {
    List< MessageBO> msgToClear = new ArrayList<>();
    if(null!=messagesToClear && !messagesToClear.isEmpty()) {
      messagesToClear.forEach(msgBo->{
        if(null!=msgBo)
          msgToClear.add(msgBo);
      });
    }
    return msgToClear;
  }

  protected List<HistoryMessageBO> constructHistoryMessage(List<MessageBO> messagesToClear) {
    List<HistoryMessageBO> MessagesToHistory = new ArrayList<>();
    messagesToClear.forEach(msgBo -> {
      if (null != msgBo) {
        HistoryMessageBO historyMsg = new HistoryMessageBO();
        BeanUtils.copyProperties(msgBo, historyMsg);
        MessagesToHistory.add(historyMsg);
      }
    });
    return MessagesToHistory;
  }

}
