package com.moyeothon.universe.domain.dto;

import java.util.Date;
import lombok.Data;

public class RecordRequestDto {

  @Data
  public class SaveRecord {
    private Long movieId; //영화 id

    private String title; //제목

    private String body; //내용

    private String summary; //내용 요약

    private int scorePerformer; //연기력

    private int scoreDirector; //연출력

    private int scoreVisual; //영상미

    private int scoreMusic; //음악

    private int scoreArtistry; //예술성

    private Date watchDate; //시청일

    private Long ownerId; //작성자 id

    private boolean isRecommend; //추천 여부

    private boolean isPublic; //공개 여부
  }
}
