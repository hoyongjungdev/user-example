package com.example.user.application;

import jakarta.validation.constraints.NotNull;

public record RegisterUserRequest(
        @NotNull String email,
        @NotNull String nickname,
        @NotNull String password,
        @NotNull String name,
        @NotNull String phoneNumber,
        @NotNull String authCode
) {
}
