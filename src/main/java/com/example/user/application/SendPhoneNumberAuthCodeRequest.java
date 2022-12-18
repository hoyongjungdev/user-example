package com.example.user.application;

import jakarta.validation.constraints.NotNull;

public record SendPhoneNumberAuthCodeRequest(
        @NotNull String email,
        @NotNull String phoneNumber
) {
}
