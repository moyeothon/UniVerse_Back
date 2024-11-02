package com.moyeothon.universe.repository;

import com.moyeothon.universe.domain.Comment;
import com.moyeothon.universe.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
