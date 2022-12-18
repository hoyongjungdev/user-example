package com.example.user.infra;

import com.example.user.domain.AuthCodeSender;
import com.example.user.domain.value.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class MockAuthCodeSender implements AuthCodeSender {
    @Override
    public void send(PhoneNumber phoneNumber, String authCode) {
        // 동작 없음
    }
}
