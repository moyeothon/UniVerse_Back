package com.moyeothon.universe.domain;

import com.moyeothon.universe.domain.dto.PostUpdateReqDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    private String title;
    private String contents;
    private String theaterName;
    private String location;
    private String openChatLink;

    //작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Post(String title, String contents , String theaterName, String location, String openChatLink, Member member){
        this.title = title;
        this.contents = contents;
        this.theaterName = theaterName;
        this.location = location;
        this.openChatLink = openChatLink;
        this.member = member;
    }

    public void update(PostUpdateReqDto postUpdateReqDto){
        this.title = postUpdateReqDto.title();
        this.contents = postUpdateReqDto.contents();
        this.theaterName = postUpdateReqDto.theaterName();
        this.location = postUpdateReqDto.location();
        this.openChatLink = postUpdateReqDto.openChatLink();
    }


}
