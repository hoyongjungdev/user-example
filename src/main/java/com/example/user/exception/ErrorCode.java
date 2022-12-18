package com.example.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "올바르지 않은 입력입니다."),
    INVALID_AUTH_CODE(HttpStatus.BAD_REQUEST, "올바르지 않은 인증 번호입니다."),
    DUPLICATE_EMAIL_ADDRESS(HttpStatus.BAD_REQUEST, "중복된 이메일 주소입니다."),
    DUPLICATE_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "중복된 전화번호입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "잘못된 로그인 인증 정보입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
