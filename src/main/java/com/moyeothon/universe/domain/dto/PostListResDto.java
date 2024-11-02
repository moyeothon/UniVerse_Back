package com.moyeothon.universe.domain.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PostListResDto(
        List<PostInfoResDto> posts
) {
    public static PostListResDto from(List<PostInfoResDto> posts) {
        return PostListResDto.builder()
                .posts(posts)
                .build();
    }
}
