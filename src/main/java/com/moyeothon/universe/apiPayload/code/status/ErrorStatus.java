package com.moyeothon.universe.apiPayload.code.status;

import com.moyeothon.universe.apiPayload.code.BaseErrorCode;
import com.moyeothon.universe.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 멤버 관려 에러
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4001", "존재하지 않는 사용자 입니다."),
    MEMBER_INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "MEMBER4002", "비밀번호가 옳지 않습니다."),
    MEMBER_USERNAME_ALREADY_EXIST(HttpStatus.CONFLICT, "MEMBER4003", "이미 존재하는 사용자입니다."),
    MEMBER_EMAIL_ALREADY_EXIST(HttpStatus.CONFLICT, "MEMBER4003", "이미 사용중인 이메일입니다."),
    MEMBER_PASSWORD_NOT_MATCHED(HttpStatus.NOT_ACCEPTABLE, "MEMBER4002", "비밀번호가 옳지 않습니다."),

    RECORD_ALREADY_EXISTS(HttpStatus.CONFLICT, "RECORD4001", "이미 존재하는 기록입니다."),
    RECORD_NOT_FOUND(HttpStatus.NOT_FOUND, "RECORD4001", "존재하지 않는 기록입니다."),

    FILE_DUPLICATE(HttpStatus.CONFLICT, "FILE4001", "이미 존재하는 파일입니다."),
    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "FILE4002", "존재하지 않는 파일입니다."),
    FILE_FAIL_DELETE(HttpStatus.INTERNAL_SERVER_ERROR, "FILE4003", "파일 삭제에 실패하였습니다."),
    FILE_FAIL_UPLOAD(HttpStatus.INTERNAL_SERVER_ERROR, "FILE4004", "파일 업로드에 실패하였습니다."),
    FILE_NOT_SUPPORTED_EXTENSION(HttpStatus.BAD_REQUEST, "FILE4005", "지원하지 않는 확장자입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
