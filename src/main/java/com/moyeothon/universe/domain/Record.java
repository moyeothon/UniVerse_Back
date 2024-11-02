package com.moyeothon.universe.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.moyeothon.universe.domain.common.BaseEntity;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "RECORD")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@Builder
public class Record extends BaseEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id; //primary Key

  private String title; //제목

  private String body; //내용

  private String summary; //내용

  private int scorePerformer; //연기력

  private int scoreDirector; //연출력

  private int scoreVisual; //영상미

  private int scoreMusic; //음악

  private int scoreArtistry; //예술성

  private Date watchDate; //시청일

  private String watchLocation; //시청 장소

  private boolean isRecommend; //추천 여부

  private boolean isPublic; //공개 여부

  @ElementCollection(fetch = FetchType.LAZY)
  private List<String> imageUrls; //이미지 url

  @OneToOne
  private Member owner; //작성자

  @ManyToOne
  private Movie movie; //영화
}
