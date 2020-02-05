package com.chat.simple.util;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chat.simple.dto.SlackMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SlackUtil {
  
  @Autowired ObjectMapper objectMapper;

  private static String slackWebhookUrl = "https://hooks.slack.com/services/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";
  
  public void sendSlackMessage(SlackMessage sm) {
    CloseableHttpClient httClient = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(slackWebhookUrl);
    try {
      String stringMsg = objectMapper.writeValueAsString(sm);
      StringEntity stringEntity = new StringEntity(stringMsg);
    
      httpPost.setEntity(stringEntity);
      httpPost.setHeader("Accept", "application/json");
      httpPost.setHeader("Content-type", "application/json");
      
      System.out.println(httpPost);
      
      CloseableHttpResponse httpResp = httClient.execute(httpPost);
      System.out.println(httpResp);
      httClient.close();
      
      
      
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
