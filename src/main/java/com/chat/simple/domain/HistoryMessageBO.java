package com.chat.simple.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mc_chat_message_history")
@Setter
@Getter
public class HistoryMessageBO {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "MC_CHAT_MSG_SEQ")
  private Long messageSeqNo;

  @Column(name = "MC_USER_MSG_D", nullable = false, columnDefinition = "VARCHAR(40)")
  private String messageKey;
  @Column(name = "MC_FROM_USER_D", nullable = false)
  private String fromUserGuid;
  @Column(name = "MC_TO_USER_D", nullable = false)
  private String toUserGuid;
  @Column(name = "MC_MSG")
  private String message;
  @Column(name = "MC_CAPTURED_S")
  private Date capturedTimestamp;
  @Column(name = "MC_SENT_S")
  private Date sentTimestamp;
  @Column(name = "MC_DELIVERED_S")
  private Date delivedTimestamp;
  @Column(name = "MC_READ_S")
  private Date readTimestamp;


}
