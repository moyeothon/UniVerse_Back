package org.likelion.moyeothon.post.domain.repository;

import org.likelion.moyeothon.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
   List<Post> findByMember(Member member);
}
