package com.moyeothon.universe.domain.dto;

import com.moyeothon.universe.domain.Comment;
import lombok.Builder;

public record CommentInfoResDto(
        String memberName,
        String content,
        String createdAt
) {
    @Builder
    public CommentInfoResDto(String memberName, String content, String createdAt) {
        this.memberName = memberName;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static CommentInfoResDto from(Comment comment) {
        return CommentInfoResDto.builder()
                .memberName(comment.getMemberName())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt().toString())
                .build();
    }
}