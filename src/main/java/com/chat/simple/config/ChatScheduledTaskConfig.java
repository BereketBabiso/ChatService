package com.chat.simple.config;

import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.chat.simple.service.ChatHistoryService;
import com.chat.simple.util.ChatServiceUtility;

@Configuration
@EnableScheduling
public class ChatScheduledTaskConfig {

  @Autowired
  private ChatHistoryService historyService;

  @Scheduled(cron = "${application.envis.schedule.cron}")
  public void runScheduledTask() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    System.out
        .println("Scheduled task running : " + ChatServiceUtility.formatCurrentTimeStamp(sdf));

    historyService.clearAlreadyDeliveredMessages();

    System.out.println("Clearing is Done ");

  }

}
