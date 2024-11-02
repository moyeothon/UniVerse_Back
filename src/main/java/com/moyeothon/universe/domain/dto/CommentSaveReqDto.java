package com.moyeothon.universe.domain.dto;

import com.moyeothon.universe.domain.Member;

public record CommentSaveReqDto(
        Long postId,
        Member member,
        String content
) {}
