package com.example.user.domain.entity;

import com.example.user.domain.value.EmailAddress;
import com.example.user.domain.value.FullName;
import com.example.user.domain.value.Nickname;
import com.example.user.domain.value.Password;
import com.example.user.domain.value.PhoneNumber;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    Integer id;

    @Embedded
    @Getter
    EmailAddress emailAddress;

    @Embedded
    @Getter
    Nickname nickname;

    @Embedded
    @Getter
    Password password;

    @Embedded
    @Getter
    FullName fullName;

    @Embedded
    @Getter
    PhoneNumber phoneNumber;

    public User(
            EmailAddress emailAddress,
            Nickname nickname,
            Password password,
            FullName fullName,
            PhoneNumber phoneNumber
    ) {
        this.emailAddress = emailAddress;
        this.nickname = nickname;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    protected User() {
    }

    public void changePassword(Password password) {
        this.password = password;
    }
}
