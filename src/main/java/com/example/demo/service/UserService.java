package com.example.demo.service;

import com.example.demo.dto.CredentailDto;
import com.example.demo.dto.UserGetDto;
import com.example.demo.dto.UserPostDto;
import com.example.demo.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void addUser(UserPostDto userPostDto);
    UserGetDto login(CredentailDto credentailDto);
    UserEntity getUser(String firstName);

}
