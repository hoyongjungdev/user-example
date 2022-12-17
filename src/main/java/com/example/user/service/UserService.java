package com.example.user.service;

import com.example.user.domain.AuthCodeChecker;
import com.example.user.domain.entity.User;
import com.example.user.domain.repository.UserRepository;
import com.example.user.domain.value.EmailAddress;
import com.example.user.domain.value.FullName;
import com.example.user.domain.value.Nickname;
import com.example.user.domain.value.Password;
import com.example.user.domain.value.PhoneNumber;
import com.example.user.exception.DuplicateEmailAddressException;
import com.example.user.exception.DuplicatePhoneNumberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthCodeChecker authCodeChecker;
    private final UserRepository userRepository;

    public void registerUser(
            EmailAddress emailAddress,
            Nickname nickname,
            Password password,
            FullName fullName,
            PhoneNumber phoneNumber,
            String authCode
    ) {
        authCodeChecker.check(emailAddress, phoneNumber, authCode);

        if (userRepository.existsByEmailAddress(emailAddress)) {
            throw new DuplicateEmailAddressException();
        }

        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DuplicatePhoneNumberException();
        }

        User user = new User(
                emailAddress,
                nickname,
                password,
                fullName,
                phoneNumber
        );

        userRepository.save(user);
    }
}
