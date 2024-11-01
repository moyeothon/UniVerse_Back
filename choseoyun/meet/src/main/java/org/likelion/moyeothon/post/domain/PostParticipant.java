package org.likelion.moyeothon.post.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
public class PostParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private LocalDateTime joinedAt;


    protected PostParticipant() {
    }


    public PostParticipant(Post post, Member member) {
        this.post = post;
        this.member = member;
        this.joinedAt = LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public Member getMember() {
        return member;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }
}
