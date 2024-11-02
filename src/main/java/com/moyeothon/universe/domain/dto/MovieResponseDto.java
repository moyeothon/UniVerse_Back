package com.moyeothon.universe.domain.dto;

import com.moyeothon.universe.domain.Movie;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

public class MovieResponseDto {

  @Data
  public static class GetInfo {
    private Long id; //primary Key

    private String title; //제목

    private String posterUrl; //포스터 URL

    private String subtitle; //부제

    private String releaseDate; //개봉일

    private String actors; //배우

    private String directors; //감독

    private int recommendCount; //추천수

    public GetInfo(Movie movie, int recommendCount) {
      this.id = movie.getId();
      this.title = movie.getTitle();
      this.posterUrl = movie.getPosterUrl();
      this.subtitle = movie.getSubtitle();
      this.releaseDate = movie.getReleaseDate();
      this.actors = movie.getActors();
      this.directors = movie.getDirectors();
      this.recommendCount = recommendCount;
    }
  }
}
