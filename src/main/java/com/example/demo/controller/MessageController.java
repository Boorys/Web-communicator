package com.example.demo.controller;

import com.example.demo.dto.MessageGetDto;
import com.example.demo.dto.MessagePostDto;
import com.example.demo.mapper.MessageMapper;
import com.example.demo.service.MessageService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@CrossOrigin
public class MessageController {

    private MessageService messageService;
    private MessageMapper messageMapper;

    public MessageController(MessageService messageService, MessageMapper messageMapper) {
        this.messageService = messageService;
        this.messageMapper = messageMapper;
    }



    @MessageMapping("/hello/{id}")
    @SendTo("/topic/greetings/{id}")
    public MessageGetDto greeting(@DestinationVariable String id, @RequestBody MessagePostDto messagePostDto) throws Exception {

        MessageGetDto messageGetDto = messageMapper.messagePostDtoToMessageGetDto(messagePostDto);
        messageService.addMessage(messagePostDto);
        return messageGetDto;

    }

}
