package com.example.user;

import com.example.user.application.RegisterUserRequest;
import com.example.user.application.SuccessResponse;
import com.example.user.domain.value.EmailAddress;
import com.example.user.domain.value.FullName;
import com.example.user.domain.value.Nickname;
import com.example.user.domain.value.Password;
import com.example.user.domain.value.PhoneNumber;
import com.example.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("users")
    public SuccessResponse registerUser(@RequestBody @Valid RegisterUserRequest request) {
        userService.registerUser(
                new EmailAddress(request.email()),
                new Nickname(request.nickname()),
                new Password(request.password()),
                new FullName(request.name()),
                new PhoneNumber(request.phoneNumber()),
                request.authCode()
        );

        return new SuccessResponse(true);
    }
}
