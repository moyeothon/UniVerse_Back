package org.likelion.moyeothon.post.domain.repository;

import org.likelion.moyeothon.post.domain.PostParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface postParticipantRepository extends JpaRepository<PostParticipant, Long> {
    Optional<PostParticipant> findByPostIdAndUserId(Long postId, Long userId);
    void deleteByPostId(Long postId);
}
