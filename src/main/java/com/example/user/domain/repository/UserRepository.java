package com.example.user.domain.repository;

import com.example.user.domain.entity.User;
import com.example.user.domain.value.EmailAddress;
import com.example.user.domain.value.PhoneNumber;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    boolean existsByEmailAddress(EmailAddress emailAddress);

    boolean existsByPhoneNumber(PhoneNumber phoneNumber);

    Optional<User> findByEmailAddress(EmailAddress emailAddress);

    Optional<User> findByPhoneNumber(PhoneNumber phoneNumber);

    Optional<User> findById(Integer id);
}
