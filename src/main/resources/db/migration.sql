# 사용자 생성

create user 'userexample'@'%' identified by 'my-secret-code';

# 데이터베이스 생성

create database userexample;
grant all privileges on userexample.* to 'userexample'@'%';

use userexample;

# users 테이블 생성

create table users (
    id integer auto_increment primary key,
    email varchar(50) not null,
    nickname varchar(15) not null,
    password varchar(50) not null,
    name varchar(15) not null,
    phone_number varchar(11) not null
);