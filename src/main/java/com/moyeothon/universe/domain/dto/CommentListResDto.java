package com.moyeothon.universe.domain.dto;

import lombok.Builder;

import java.util.List;

public record CommentListResDto(
        List<CommentInfoResDto> comments,
        int totalCount
) {
    @Builder
    public CommentListResDto(List<CommentInfoResDto> comments, int totalCount) {
        this.comments = comments;
        this.totalCount = totalCount;
    }

    public static CommentListResDto from(List<CommentInfoResDto> comments) {
        return CommentListResDto.builder()
                .comments(comments)
                .totalCount(comments.size())
                .build();
    }
}
