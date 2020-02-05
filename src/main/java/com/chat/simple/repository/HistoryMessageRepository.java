package com.chat.simple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.chat.simple.domain.HistoryMessageBO;

@Repository
public interface HistoryMessageRepository extends JpaRepository<HistoryMessageBO, Long> {

}
