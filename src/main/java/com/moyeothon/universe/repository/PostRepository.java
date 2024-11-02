package com.moyeothon.universe.repository;

import com.moyeothon.universe.domain.Member;
import com.moyeothon.universe.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMember(Member member);
}
