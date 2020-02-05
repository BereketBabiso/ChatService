package com.chat.simple.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "MYCHAT_USER_PROFILE")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfileBO {

  @Id
  @Column(name = "MC_USER_D")
  private String userId;
  @Column(name = "MC_USER_FIRST_N", nullable = false)
  private String firstName;
  @Column(name = "MC_USER_LAST_N", nullable = false)
  private String lastName;
  @Column(name = "MC_USER_PHONE")
  private String phone;
  @Column(name = "MC_USER_EMAIL")
  private String email;

}
