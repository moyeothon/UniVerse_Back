package org.likelion.moyeothon.post.api.dto.response;

import lombok.Builder;
import org.likelion.moyeothon.post.domain.Post;

@Builder
public record PostInfoResDto(
        String title,
        String contents,
        String writer
) {
    public static PostInfoResDto from(Post post){
        return PostInfoResDto.builder()
                .title(post.getTitle())
                .contents(post.getContents())
                //.writer(post.gerUser().getName())
                .build();
    }
}
