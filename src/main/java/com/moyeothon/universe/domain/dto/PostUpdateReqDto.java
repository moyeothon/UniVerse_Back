package com.moyeothon.universe.domain.dto;

public record PostUpdateReqDto(
        String title,
        String contents,
        String theaterName,
        String location,
        String openChatLink
) {
}

