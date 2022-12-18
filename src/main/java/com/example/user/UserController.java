package com.example.user;

import com.example.user.application.GetUserResponse;
import com.example.user.application.LoginByEmailRequest;
import com.example.user.application.LoginByPhoneRequest;
import com.example.user.application.RegisterUserRequest;
import com.example.user.application.ResetPasswordRequest;
import com.example.user.application.SendPhoneNumberAuthCodeRequest;
import com.example.user.application.SuccessResponse;
import com.example.user.application.UserDto;
import com.example.user.application.UserIdResponse;
import com.example.user.domain.entity.User;
import com.example.user.domain.repository.UserRepository;
import com.example.user.domain.value.EmailAddress;
import com.example.user.domain.value.FullName;
import com.example.user.domain.value.Nickname;
import com.example.user.domain.value.Password;
import com.example.user.domain.value.PhoneNumber;
import com.example.user.exception.UserNotFoundException;
import com.example.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("users/authenticate/phone")
    public SuccessResponse sendPhoneNumberAuthCode(@RequestBody @Valid SendPhoneNumberAuthCodeRequest request) {
        userService.sendPhoneNumberAuthCode(
                new EmailAddress(request.email()),
                new PhoneNumber(request.phoneNumber())
        );

        return new SuccessResponse(true);
    }

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

    @PostMapping("users/login-by-email")
    public UserIdResponse loginByEmail(@RequestBody @Valid LoginByEmailRequest request) {
        int userId = userService.loginByEmail(
                new EmailAddress(request.email()),
                new Password(request.password())
        );

        return new UserIdResponse(
                true,
                userId
        );
    }

    @PostMapping("users/login-by-phone")
    public UserIdResponse loginByPhone(@RequestBody @Valid LoginByPhoneRequest request) {
        int userId = userService.loginByPhone(
                new PhoneNumber(request.phoneNumber()),
                new Password(request.password())
        );

        return new UserIdResponse(
                true,
                userId
        );
    }

    @GetMapping("users/{userId}")
    public GetUserResponse getUser(@PathVariable int userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException();
        }

        User user = userOptional.get();

        return new GetUserResponse(
                true,
                new UserDto(
                        user.getEmailAddress().getValue(),
                        user.getNickname().getValue(),
                        user.getFullName().getValue(),
                        user.getPhoneNumber().getValue()
                )
        );
    }

    @PutMapping("users/password")
    public SuccessResponse resetPassword(@RequestBody @Valid ResetPasswordRequest request) {
        userService.resetPassword(
                new EmailAddress(request.email()),
                new PhoneNumber((request.phoneNumber())),
                request.authCode(),
                new Password(request.password())
        );

        return new SuccessResponse(true);
    }
}
