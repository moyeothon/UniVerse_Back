package com.moyeothon.universe.apiPayload.code.status;

import com.moyeothon.universe.apiPayload.code.BaseCode;
import com.moyeothon.universe.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 일반적인 응답
    _OK(HttpStatus.OK, "COMMON200", "성공입니다."),

    // 멤버 관련 응답
    MEMBER_FOUND(HttpStatus.OK,"MEMBER2001", "회원 조회 성공"),
    MEMBER_UPDATE(HttpStatus.OK, "MEMBER2002", "회원 정보 업데이트 성공"),
    MEMBER_UPDATE_PASSWORD(HttpStatus.OK, "MEMBER2003", "회원 비밀번호 업데이트 성공"),
    MEMBER_DELETE(HttpStatus.OK, "MEMBER2004", "회원 탈퇴 성공"),
    MEMBER_JOIN(HttpStatus.OK, "MEMBER2005", "회원 가입 성공"),
    MEMBER_LOGIN(HttpStatus.OK, "MEMBER2006", "로그인 성공"),

    // MOVIE 관련 응답
    MOVIE_ADD(HttpStatus.OK, "MOVIE2000", "MOVIE 추가 성공"),
    MOVIE_GET_ONE(HttpStatus.OK, "MOVIE2001", "MOVIE 단건 조회 성공"),
    MOVIE_GET_ALL(HttpStatus.OK, "MOVIE2002", "MOVIE 전체 조회 성공"),
    MOVIE_GET_RECORD(HttpStatus.OK, "MOVIE2003", "MOVIE RECORD 요약 조회 성공"),

    // MOVIE 관련 응답
    RECORD_ADD(HttpStatus.OK, "RECORD2000", "RECORD 추가 성공"),
    RECORD_GET(HttpStatus.OK, "RECORD2001", "RECORD 조회 성공"),
    RECORD_GET_MY(HttpStatus.OK, "RECORD2002", "나의 RECORD 조회 성공"),
    RECORD_IMAGE_ADD(HttpStatus.OK, "RECORD2003", "RECORD 이미지 추가 성공")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}
