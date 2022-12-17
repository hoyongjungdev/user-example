package com.example.user.domain.value;

import com.example.user.exception.ValidationException;
import com.example.user.util.RegexUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public class PhoneNumber {
    @Column(name = "phone_number")
    @Getter
    private String value;

    public PhoneNumber(String value) {
        String pattern = "^[0-9]{9,11}$";

        if (!RegexUtil.match(value, pattern)) {
            throw new ValidationException("올바르지 않은 전화번호 형식입니다.");
        }

        this.value = value;
    }

    protected PhoneNumber() {
        this.value = "";
    }
}
