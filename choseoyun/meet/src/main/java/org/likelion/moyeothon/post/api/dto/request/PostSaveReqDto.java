package org.likelion.moyeothon.post.api.dto.request;

public record PostSaveReqDto(
        //작성자 아이디, 제목, 내용
        Long userId,
        String title,
        String contents
) {
}
