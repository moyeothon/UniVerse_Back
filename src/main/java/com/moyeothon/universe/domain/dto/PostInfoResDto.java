package com.moyeothon.universe.domain.dto;

import com.moyeothon.universe.domain.Member;
import com.moyeothon.universe.domain.Post;
import lombok.Builder;

@Builder
public record PostInfoResDto(
        String title,
        String contents,
        String theaterName,
        String location,
        String openChatLink,
        String writer
) {
    public static PostInfoResDto from(Post post, Member member){
        return PostInfoResDto.builder()
                .title(post.getTitle())
                .contents(post.getContents())
                .theaterName(post.getTheaterName())
                .location(post.getLocation())
                .openChatLink(post.getOpenChatLink())
                .writer(post.getMember().getName())
                .build();
    }
}
