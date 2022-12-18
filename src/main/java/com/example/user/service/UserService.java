package com.example.user.service;

import com.example.user.domain.AuthCodeChecker;
import com.example.user.domain.AuthCodeSender;
import com.example.user.domain.entity.User;
import com.example.user.domain.repository.UserRepository;
import com.example.user.domain.value.EmailAddress;
import com.example.user.domain.value.FullName;
import com.example.user.domain.value.Nickname;
import com.example.user.domain.value.Password;
import com.example.user.domain.value.PhoneNumber;
import com.example.user.exception.DuplicateEmailAddressException;
import com.example.user.exception.DuplicatePhoneNumberException;
import com.example.user.exception.InvalidPasswordException;
import com.example.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthCodeChecker authCodeChecker;
    private final AuthCodeSender authCodeSender;
    private final UserRepository userRepository;

    public void sendPhoneNumberAuthCode(EmailAddress emailAddress, PhoneNumber phoneNumber) {
        String authCode = authCodeChecker.generate(emailAddress, phoneNumber);
        authCodeSender.send(phoneNumber, authCode);
    }

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

    public int loginByEmail(EmailAddress emailAddress, Password password) {
        Optional<User> userOptional = userRepository.findByEmailAddress(emailAddress);

        return getUserIdAndCheckPassword(password, userOptional);
    }

    public int loginByPhone(PhoneNumber phoneNumber, Password password) {
        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);

        return getUserIdAndCheckPassword(password, userOptional);
    }

    @Transactional
    public void resetPassword(
            EmailAddress emailAddress,
            PhoneNumber phoneNumber,
            String authCode,
            Password password
    ) {
        Optional<User> userOptional = userRepository.findByEmailAddress(emailAddress);

        UserNotFoundException notFoundException = new UserNotFoundException();

        if (userOptional.isEmpty()) {
            throw notFoundException;
        }

        User user = userOptional.get();

        if (!user.getPhoneNumber().equals(phoneNumber)) {
            throw notFoundException;
        }

        authCodeChecker.check(emailAddress, phoneNumber, authCode);

        user.changePassword(password);
    }

    private int getUserIdAndCheckPassword(Password password, Optional<User> userOptional) {
        InvalidPasswordException exception = new InvalidPasswordException();

        if (userOptional.isEmpty()) {
            throw exception;
        }

        User user = userOptional.get();

        if (!user.getPassword().equals(password)) {
            throw exception;
        }

        return user.getId();
    }
}
