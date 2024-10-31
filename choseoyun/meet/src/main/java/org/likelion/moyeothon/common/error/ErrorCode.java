package org.likelion.moyeothon.common.error;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {
    /**
     * 404 NOT FOUND: 서버가 요청받은 자원을 찾을 수 없을 때 나타나는 오류
     */
    POSTS_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 게시글이 없습니다. postId = ", "NOT_FOUND_404"),
    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 사용자가 없습니다. userId = ", "USER_NOT_FOUND_404"),

    /**
     * 409 CONFLICT: 요청이 현재 서버 상태와 충돌하는 경우
     */
    DUPLICATE_PARTICIPATION_ERROR(HttpStatus.CONFLICT, "이미 참여한 구인글입니다. postId = ", "CONFLICT_409"),


    /**
     * 500 INTERNAL SERVER ERROR: 서버가 요청을 이행할 수 없는 예상치 못한 상황에 직면한 경우
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다", "INTERNAL_SERVER_ERROR_500"),

    /**
     * 400 BAD REQUEST
     */
    NO_PARTICIPATION_RECORD_ERROR(HttpStatus.BAD_REQUEST, "참여 기록이 존재하지 않습니다.", "BAD_REQUEST_400"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "유효성 검사에 맞지 않습니다.", "BAD_REQUEST_400");


    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
