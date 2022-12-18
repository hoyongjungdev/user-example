package com.example.user.application;

import jakarta.validation.constraints.NotNull;

public record ResetPasswordRequest(
        @NotNull String email,
        @NotNull String phoneNumber,
        @NotNull String authCode,
        @NotNull String password
) {
}
