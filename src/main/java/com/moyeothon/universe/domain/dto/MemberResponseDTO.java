package com.moyeothon.universe.domain.dto;

import lombok.Builder;
import lombok.Data;

public class MemberResponseDTO {

  @Data
  @Builder
  public static class LoginResultDTO{
    private String accessToken;
    private String refreshToken;
  }
}
