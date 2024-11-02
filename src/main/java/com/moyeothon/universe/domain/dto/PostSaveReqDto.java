package com.moyeothon.universe.domain.dto;

public record PostSaveReqDto(
        //작성자 아이디, 제목, 내용, 영화관, 지역, 오픈 채팅 링크
        Long memberId,
        String title,
        String contents,
        String theaterName,
        String location,
        String openChatLink
) {
}
