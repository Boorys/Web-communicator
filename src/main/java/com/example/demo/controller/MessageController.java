package com.example.demo.controller;

import com.example.demo.dto.MessageGetDto;
import com.example.demo.dto.MessagePostDto;
import com.example.demo.mapper.MessageMapper;
import com.example.demo.service.MessageServiceImpl;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("${client.url}")
public class MessageController {

    private MessageServiceImpl messageServiceImpl;
    private MessageMapper messageMapper;

    public MessageController(MessageServiceImpl messageServiceImpl, MessageMapper messageMapper) {
        this.messageServiceImpl = messageServiceImpl;
        this.messageMapper = messageMapper;
    }



    @MessageMapping("/hello/{id}")
    @SendTo("/topic/greetings/{id}")
    public MessageGetDto greeting(@DestinationVariable String id, @RequestBody MessagePostDto messagePostDto) throws Exception {

        MessageGetDto messageGetDto = messageMapper.messagePostDtoToMessageGetDto(messagePostDto);
        messageServiceImpl.addMessage(messagePostDto);
        return messageGetDto;

    }

}
