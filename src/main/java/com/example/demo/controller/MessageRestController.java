package com.example.demo.controller;

import com.example.demo.dto.MessageGetDto;
import com.example.demo.dto.MessagePostDto;
import com.example.demo.service.MessageServiceImpl;
import com.example.demo.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("${client.url}")
public class MessageRestController {


    private UserServiceImpl userServiceImpl;
    private MessageServiceImpl messageServiceImpl;

    public MessageRestController(UserServiceImpl userServiceImpl, MessageServiceImpl messageServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.messageServiceImpl = messageServiceImpl;
    }


    @PostMapping(path = "messages")
    @ResponseStatus(value = HttpStatus.OK)
    public void addMessage(@RequestBody MessagePostDto messagePostDto) {
        messageServiceImpl.addMessage(messagePostDto);
    }

    @GetMapping(path = "messages/{page}/{friendId}")
    public List<MessageGetDto> messageGetDto(@PathVariable int page, @PathVariable String friendId) {
        return messageServiceImpl.getMessage(page, friendId);
    }


}
