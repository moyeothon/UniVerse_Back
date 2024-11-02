package com.moyeothon.universe.apiPayload.code.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {
    /**
     * 200: 요청이 성공했다는 코드
     */
    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),
    POST_UPDATE_SUCCESS(HttpStatus.OK, "게시글이 성공적으로 수정되었습니다."),
    POST_DELETE_SUCCESS(HttpStatus.OK, "게시글이 성공적으로 삭제되었습니다."),
    COMMENT_UPDATE_SUCCESS(HttpStatus.OK, "댓글이 성공적으로 수정되었습니다."),
    COMMENT_DELETE_SUCCESS(HttpStatus.OK, "댓글이 성공적으로 삭제되었습니다."),

    /**
     * 201 CREATED: 요청이 성공했고, 자원의 생성이 일어났다는 코드
     */
    POST_SAVE_SUCCESS(HttpStatus.CREATED, "게시글이 성공적으로 등록되었습니다."),
    COMMENT_SAVE_SUCCESS(HttpStatus.CREATED, "댓글이 성공적으로 등록되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
