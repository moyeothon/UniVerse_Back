package com.moyeothon.universe.domain.dto;

import com.moyeothon.universe.domain.Comment;
import com.moyeothon.universe.domain.Member;
import lombok.Builder;


public record CommentInfoResDto(
        Member member,
        String content,
        String createdAt
) {
    public static CommentInfoResDto from(Comment comment) {
        return new CommentInfoResDto(
                comment.getMember(),
                comment.getContent(),
                comment.getCreatedAt().toString()
        );
    }
}
