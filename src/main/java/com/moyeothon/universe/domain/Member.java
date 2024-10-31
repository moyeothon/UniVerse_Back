package com.moyeothon.universe.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.moyeothon.universe.domain.common.BaseEntity;
import com.moyeothon.universe.domain.emun.MemberStatus;
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
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

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

  @Column(nullable = false)
  private String password; //비밀번호

  @Column(nullable = false, length = 30, unique = true)
  private String email; //이메일

  @Column(nullable = false)
  private String nickname; //닉네임

  @Enumerated(EnumType.STRING)
  @Setter
  private MemberStatus status;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 30)
  private Role role; //권한 -> USER, ADMIN

  private String refreshToken; //리프레시 토큰

  //== 정보 수정 ==//

  public void updatePassword(PasswordEncoder passwordEncoder, String password){
    this.password = passwordEncoder.encode(password);
  }

  public void updateRefreshToken(String refreshToken){
    this.refreshToken = refreshToken;
  }

  public void destroyRefreshToken(){
    this.refreshToken = null;
  }
  //
  //== 패스워드 암호화 ==//
  public void encodePassword(PasswordEncoder passwordEncoder){
    this.password = passwordEncoder.encode(password);
  }

  //비밀번호 변경, 회원 탈퇴 시, 비밀번호를 확인하며, 이때 비밀번호의 일치여부를 판단하는 메서드입니다.
  public boolean matchPassword(PasswordEncoder passwordEncoder, String checkPassword){
    return passwordEncoder.matches(checkPassword, getPassword());
  }
}