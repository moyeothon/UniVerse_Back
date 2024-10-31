package org.likelion.moyeothon.post.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.moyeothon.post.api.dto.request.PostUpdateReqDto;

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

    //작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    private Post(String title, String contents , User user){
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public void update(PostUpdateReqDto postUpdateReqDto){
        this.title = postUpdateReqDto.title();
        this.contents = postUpdateReqDto.contents();
    }


}
