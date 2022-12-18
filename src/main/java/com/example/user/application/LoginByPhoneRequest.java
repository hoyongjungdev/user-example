package com.example.user.application;

import jakarta.validation.constraints.NotNull;

public record LoginByPhoneRequest(@NotNull String phoneNumber, @NotNull String password) {
}
