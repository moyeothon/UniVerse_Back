package com.moyeothon.universe.domain.dto;

import lombok.Builder;
import lombok.Data;

public class MemberResponseDto {

  @Data
  @Builder
  public static class LoginResultDto {
    private String accessToken;
    private String refreshToken;
  }

  @Data
  @Builder
  public static class SignUpDto {
    private String username;
    private String email;
    private String nickname;
  }
}
