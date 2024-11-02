package com.moyeothon.universe.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String memberName;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public Comment(Post post, String memberName, String content) {
        this.post = post;
        this.memberName = memberName;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    // 댓글 수정
    public void updateContent(String content) {
        this.content = content;
    }
}
