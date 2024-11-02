package com.moyeothon.universe.service;

import com.moyeothon.universe.apiPayload.code.status.ErrorStatus;
import com.moyeothon.universe.apiPayload.exception.handler.MemberHandler;
import com.moyeothon.universe.domain.Member;
import com.moyeothon.universe.domain.dto.CommentInfoResDto;
import com.moyeothon.universe.domain.dto.CommentSaveReqDto;
import com.moyeothon.universe.domain.dto.CommentUpdateReqDto;
import com.moyeothon.universe.repository.CommentRepository;
import com.moyeothon.universe.repository.MemberRepository;
import com.moyeothon.universe.util.security.SecurityUtil;
import jdk.jshell.Snippet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void saveComment(CommentSaveReqDto dto) {
        /*Post post = postRepository.findById(dto.postId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. postId = " + dto.postId()));*/
        Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername()).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Comment comment = Comment.builder()
                .post(post)
                .member(member)
                .content(dto.content())
                .build();

        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentInfoResDto> getCommentsByPost(Long postId) {
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. postId = " + postId));

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
