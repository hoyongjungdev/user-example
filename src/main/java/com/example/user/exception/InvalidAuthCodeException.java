package com.example.user.exception;

public class InvalidAuthCodeException extends BaseException {
    public InvalidAuthCodeException() {
        super(ErrorCode.INVALID_AUTH_CODE);
    }
}
