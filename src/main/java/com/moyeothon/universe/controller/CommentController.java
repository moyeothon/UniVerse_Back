package com.moyeothon.universe.controller;

import com.moyeothon.universe.domain.dto.CommentInfoResDto;
import com.moyeothon.universe.domain.dto.CommentSaveReqDto;
import com.moyeothon.universe.domain.dto.CommentUpdateReqDto;
import com.moyeothon.universe.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/comments")
    public ResponseEntity<String> saveComment(@PathVariable Long postId, @RequestBody CommentSaveReqDto dto) {
        commentService.saveComment(new CommentSaveReqDto(postId, dto.member(), dto.content()));
        return ResponseEntity.ok("댓글이 성공적으로 추가되었습니다.");
    }

    // 댓글 조회
    @GetMapping
    public ResponseEntity<List<CommentInfoResDto>> getCommentsByPost(@PathVariable Long postId) {
        List<CommentInfoResDto> comments = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(comments);
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateReqDto commentUpdateReqDto) {
        commentService.updateComment(commentId, commentUpdateReqDto);
        return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
    }

}
