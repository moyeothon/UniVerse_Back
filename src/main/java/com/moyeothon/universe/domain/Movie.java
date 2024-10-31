package com.moyeothon.universe.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.moyeothon.universe.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "MOVIE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@Builder
public class Movie extends BaseEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id; //primary Key

  @Column(nullable = false, length = 30, unique = true)
  private String title; //제목

  private String posterUrl; //포스터 URL

  @Column(nullable = false)
  private String subtitle; //부제

  private String releaseDate; //개봉일

  private String actors; //배우

  private String directors; //감독
}