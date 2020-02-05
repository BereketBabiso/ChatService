package com.chat.simple.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.chat.simple.domain.MessageBO;

@Repository
public interface MessageRepository extends JpaRepository<MessageBO, Long> {

  List<MessageBO> findByToUserGuidAndSentTimestampNullOrderByCapturedTimestampDesc(String userUuid);

  MessageBO findByToUserGuidAndFromUserGuidAndMessageKeyAndDelivedTimestampNull(String userGuid,
      String fromUserGuid, String msgId);  
   
  Page<MessageBO> findAllByDelivedTimestampNotNull(Pageable pageable);

}
