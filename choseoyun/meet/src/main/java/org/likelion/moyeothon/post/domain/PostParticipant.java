package org.likelion.moyeothon.post.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
public class PostParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    //private User user;

    private LocalDateTime joinedAt;


    protected PostParticipant() {
    }


    public PostParticipant(Post post, User user) {
        this.post = post;
        this.user = user;
        this.joinedAt = LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }
}
