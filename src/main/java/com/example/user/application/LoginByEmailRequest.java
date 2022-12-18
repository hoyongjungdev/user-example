package com.example.user.application;

import jakarta.validation.constraints.NotNull;

public record LoginByEmailRequest(@NotNull String email, @NotNull String password) {
}
