package com.example.demo.mapper;

import com.example.demo.dto.MessageGetDto;
import com.example.demo.dto.MessagePostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MessageMapper {

     MessagePostDto messageGetDtoToMessagePostDto(MessageGetDto messageGetDto);
     @Mappings({
             @Mapping(target = "textMessage", source = "textMessage"),
             @Mapping(target = "userId", source = "userId"),
             @Mapping(ignore = true, target = "date"),
     })
     MessageGetDto messagePostDtoToMessageGetDto(MessagePostDto messagePostDto);
}
