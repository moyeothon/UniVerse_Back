package com.moyeothon.universe.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.moyeothon.universe.domain.common.BaseEntity;
import com.moyeothon.universe.domain.emun.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "member_id")
  private Long id; //primary Key

  @Column(nullable = false, length = 30, unique = true)
  private String username; //아이디

  private String password; //비밀번호

  private String email; //이메일

  private String nickname; //닉네임

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 30)
  private Role role; //권한 -> USER, ADMIN
}