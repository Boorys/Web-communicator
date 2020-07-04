package com.example.demo.service;

import com.example.demo.dto.CredentailDto;
import com.example.demo.dto.UserGetDto;
import com.example.demo.dto.UserPostDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.UserExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void addUser(UserPostDto userPostDto) throws UserExistException {
        UserEntity userEntity = userRepository.findByEmail(userPostDto.getEmail());
        if (userEntity != null) {
            throw new UserExistException();
        }
        userEntity = userRepository.findByFirstName(userPostDto.getFirstName());
        if (userEntity != null) {
            throw new UserExistException();
        }
        userEntity = userMapper.userPostDtoToUserEntity(userPostDto);
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUser(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(UserNotFoundException::new);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return new JwtUserDetails(1L, userEntity.getEmail(), userEntity.getPassword(), "Role");

    }
}
