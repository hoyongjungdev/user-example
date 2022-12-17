package com.example.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "올바르지 않은 입력입니다."),
    INVALID_AUTH_CODE(HttpStatus.BAD_REQUEST, "올바르지 않은 인증 번호입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
