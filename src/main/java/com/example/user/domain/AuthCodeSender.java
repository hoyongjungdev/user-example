package com.example.user.domain;

import com.example.user.domain.value.PhoneNumber;

public interface AuthCodeSender {
    void send(PhoneNumber phoneNumber, String authCode);
}
