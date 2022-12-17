package com.example.user.domain;

import com.example.user.domain.value.EmailAddress;
import com.example.user.domain.value.PhoneNumber;
import com.example.user.exception.InvalidAuthCodeException;
import org.springframework.stereotype.Component;

@Component
public class SimpleAuthCodeGenerator implements AuthCodeChecker {
    private static final String SAMPLE_AUTH_CODE = "000000";

    @Override
    public String generate(EmailAddress emailAddress, PhoneNumber phoneNumber) {
        return SAMPLE_AUTH_CODE;
    }

    @Override
    public void check(EmailAddress emailAddress, PhoneNumber phoneNumber, String authCode) {
        if (!authCode.equals(SAMPLE_AUTH_CODE)) {
            throw new InvalidAuthCodeException();
        }
    }
}
