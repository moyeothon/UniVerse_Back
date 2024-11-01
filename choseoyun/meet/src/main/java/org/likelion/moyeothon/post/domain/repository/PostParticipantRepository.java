package org.likelion.moyeothon.post.domain.repository;

import org.likelion.moyeothon.post.domain.PostParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostParticipantRepository extends JpaRepository<PostParticipant, Long> {
    Optional<PostParticipant> findByPostIdAndMemberId(Long postId, Long memberId);
    void deleteByPostId(Long postId);
}
