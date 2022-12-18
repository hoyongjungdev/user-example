package com.example.user.domain.value;

import com.example.user.exception.ValidationException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Embeddable
@EqualsAndHashCode
public class FullName {
    private static final int MAX_LENGTH = 15;

    @Getter
    @Column(name = "name")
    private String value;

    public FullName(String value) {
        if (value.isEmpty()) {
            throw new ValidationException("이름이 빈 값입니다");
        }

        if (value.length() > MAX_LENGTH) {
            throw new ValidationException("이름의 길이가 %d자를 초과했습니다.".formatted(MAX_LENGTH));
        }

        this.value = value;
    }

    protected FullName() {
        this.value = "";
    }
}
