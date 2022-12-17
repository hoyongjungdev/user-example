package com.example.user.domain.value;

import com.example.user.exception.ValidationException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public class Nickname {
    private static final int MAX_LENGTH = 15;

    @Getter
    @Column(name = "nickname")
    private String value;

    public Nickname(String value) {
        if (value.isEmpty()) {
            throw new ValidationException("닉네임이 빈 값입니다.");
        }

        if (value.length() > MAX_LENGTH) {
            throw new ValidationException("닉네임의 길이가 %d자를 초과했습니다.".formatted(MAX_LENGTH));
        }

        this.value = value;
    }

    protected Nickname() {
        this.value = "";
    }
}
