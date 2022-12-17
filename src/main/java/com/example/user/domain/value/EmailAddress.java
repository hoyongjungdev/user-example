package com.example.user.domain.value;

import com.example.user.exception.ValidationException;
import com.example.user.util.RegexUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public class EmailAddress {
    @Getter
    @Column(name = "email")
    private String value;

    protected EmailAddress() {
        this.value = "";
    }

    public EmailAddress(String value) {
        String pattern = "^([A-Za-z0-9_-]+)@([A-Za-z0-9_-]+)(\\.[A-Za-z0-9_-]+)+$";

        if (!RegexUtil.match(value, pattern)) {
            throw new ValidationException("올바르지 않은 이메일 형식입니다.");
        }

        this.value = value;
    }
}
