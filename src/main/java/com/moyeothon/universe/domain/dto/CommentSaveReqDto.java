package com.moyeothon.universe.domain.dto;

public record CommentSaveReqDto(
        Long postId,
        String memberName,
        String content
) {}
