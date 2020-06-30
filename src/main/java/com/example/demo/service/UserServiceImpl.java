package com.example.demo.service;

import com.example.demo.dto.CredentailDto;
import com.example.demo.dto.UserGetDto;
import com.example.demo.dto.UserPostDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    UserMapper userMapper;
    UserRepository userRepository;
@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(UserPostDto userPostDto) {
        UserEntity userEntity = userMapper.userPostDtoToUserEntity(userPostDto);
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public UserGetDto login(CredentailDto credentailDto) {

        UserEntity userEntity = userRepository.findByEmail(credentailDto.getEmail());
        UserGetDto userGetDto = new UserGetDto();
        userGetDto.setUserId(userEntity.getUserId());

        return userGetDto;
    }

    @Override
    public UserEntity getUser(String email)
    {
      return  userRepository.findByEmail(email);
    }



   @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

       UserEntity userEntity = userRepository.findByEmail(email);

       if (userEntity == null) throw new UsernameNotFoundException(email);
       return new JwtUserDetails(1L,userEntity.getEmail(), userEntity.getPassword(), "Role");

    }
}
