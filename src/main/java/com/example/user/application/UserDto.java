package com.example.user.application;

public record UserDto(
        String email,
        String nickname,
        String name,
        String phoneNumber
) {
}
