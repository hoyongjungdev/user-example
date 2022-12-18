package com.example.user.domain.value;

import com.example.user.exception.ValidationException;
import com.example.user.util.RegexUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Embeddable
@EqualsAndHashCode
public class Password {
    @Getter
    @Column(name = "password")
    private String value;

    public Password(String value) {
        String pattern = "^[a-zA-Z0-9@!_\\-#$%*?&]{8,50}$";

        if (!RegexUtil.match(value, pattern)) {
            throw new ValidationException("올바르지 않은 비밀번호 형식입니다.");
        }

        this.value = value;
    }

    protected Password() {
        this.value = "";
    }
}
