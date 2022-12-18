package com.example.user.domain;

import com.example.user.domain.value.EmailAddress;
import com.example.user.domain.value.PhoneNumber;
import com.example.user.exception.InvalidAuthCodeException;

public interface AuthCodeChecker {
    String generate(EmailAddress emailAddress, PhoneNumber phoneNumber);

    void check(EmailAddress emailAddress, PhoneNumber phoneNumber, String authCode)
            throws InvalidAuthCodeException;
}
