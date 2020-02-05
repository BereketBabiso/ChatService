package com.chat.simple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.chat.simple.domain.UserProfileBO;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileBO, String> {

}
