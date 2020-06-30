package com.example.demo.controller;

import com.example.demo.dto.MessageGetDto;
import com.example.demo.dto.MessagePostDto;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MessageRestController {


    private UserServiceImpl userServiceImpl;
    private MessageService messageService;

    public MessageRestController(UserServiceImpl userServiceImpl, MessageService messageService) {
        this.userServiceImpl = userServiceImpl;
        this.messageService = messageService;
    }


    @PostMapping(path = "message")
    public void addMessage(@RequestBody MessagePostDto messagePostDto) {

        messageService.addMessage(messagePostDto);
    }

    @GetMapping(path = "getMessage/{page}/{friendId}")
    public List<MessageGetDto> messageGetDto(@PathVariable int page, @PathVariable String friendId) {
        return messageService.getMessage(page, friendId);
    }


}
