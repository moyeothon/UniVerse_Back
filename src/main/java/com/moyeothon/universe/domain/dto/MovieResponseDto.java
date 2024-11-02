package com.moyeothon.universe.domain.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

public class MovieResponseDto {

  @Data
  @Builder
  public static class GetInfoDto {

    private Long id; //primary Key

    private String title; //제목

    private String posterUrl; //포스터 URL

    private String subtitle; //부제

    private String releaseDate; //개봉일

    private String actors; //배우

    private String directors; //감독

    private int recommendCount; //추천 수
  }
}
