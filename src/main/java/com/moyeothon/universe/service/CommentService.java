package com.moyeothon.universe.service;

import com.moyeothon.universe.domain.Comment;
import com.moyeothon.universe.domain.Post;
import com.moyeothon.universe.domain.dto.CommentInfoResDto;
import com.moyeothon.universe.domain.dto.CommentSaveReqDto;
import com.moyeothon.universe.domain.dto.CommentUpdateReqDto;
import com.moyeothon.universe.repository.CommentRepository;
import com.moyeothon.universe.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public void saveComment(CommentSaveReqDto dto) {
        Post post = postRepository.findById(dto.postId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. postId = " + dto.postId()));

        Comment comment = Comment.builder()
                .post(post)
                .memberName(dto.memberName())
                .content(dto.content())
                .build();

        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentInfoResDto> getCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. postId = " + postId));

        return commentRepository.findByPost(post).stream()
                .map(CommentInfoResDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateComment(Long commentId, CommentUpdateReqDto commentUpdateReqDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. commentId = " + commentId));

        comment.updateContent(commentUpdateReqDto.content());
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. commentId = " + commentId));

        commentRepository.delete(comment);
    }
}