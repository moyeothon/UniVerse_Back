package org.likelion.moyeothon.post.api.dto.request;

public record PostUpdateReqDto(
        String title,
        String contents,
        String theaterName,
        String location,
        String openChatLink
) {
}
