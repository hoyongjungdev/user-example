package com.example.user.exception;

public class DuplicateEmailAddressException extends BaseException {
    public DuplicateEmailAddressException() {
        super(ErrorCode.DUPLICATE_EMAIL_ADDRESS);
    }
}
