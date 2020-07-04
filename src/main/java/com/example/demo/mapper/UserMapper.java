package com.example.demo.mapper;

import com.example.demo.dto.UserPostDto;
import com.example.demo.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity userPostDtoToUserEntity(UserPostDto userPostDto);
}
