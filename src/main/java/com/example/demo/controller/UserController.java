package com.example.demo.controller;

import com.example.demo.dto.CredentailDto;
import com.example.demo.dto.FriendPostDto;
import com.example.demo.dto.UserGetDto;
import com.example.demo.dto.UserPostDto;
import com.example.demo.service.FriendService;
import com.example.demo.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    private UserServiceImpl userServiceImpl;

    private FriendService friendService;

    private  TopicService topicService;

    public UserController(UserServiceImpl userServiceImpl, FriendService friendService, TopicService topicService) {
        this.userServiceImpl = userServiceImpl;
        this.friendService = friendService;
        this.topicService = topicService;
    }

    @PostMapping(path = "/add/user")
    public void addUser(@RequestBody UserPostDto userPostDto) {
        userServiceImpl.addUser(userPostDto);
    }

    @PostMapping(path = "/add/friend")
    public void addFriend(@RequestBody FriendPostDto friendPostDro) {
        friendService.addFriend(friendPostDro);
    }

    @PostMapping(path = "login")
    public UserGetDto login(@RequestBody CredentailDto credentailDto) {
        return userServiceImpl.login(credentailDto);
    }

    @GetMapping(path="find/friend/{name}")
    public List<UserGetDto> findFriend(@PathVariable String name)
    {
        return friendService.getFriendByFirstName(name);
    }

}
