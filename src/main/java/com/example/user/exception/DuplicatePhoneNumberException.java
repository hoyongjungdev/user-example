package com.example.user.exception;

public class DuplicatePhoneNumberException extends BaseException {
    public DuplicatePhoneNumberException() {
        super(ErrorCode.DUPLICATE_PHONE_NUMBER);
    }
}
