package com.example.demo.controller;

import com.example.demo.dto.FriendPostDto;
import com.example.demo.dto.UserGetDto;
import com.example.demo.dto.UserPostDto;
import com.example.demo.service.FriendServiceImpl;
import com.example.demo.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("${client.url}")
public class UserController {

    private UserServiceImpl userServiceImpl;
    private FriendServiceImpl friendServiceImpl;

    public UserController(UserServiceImpl userServiceImpl, FriendServiceImpl friendServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.friendServiceImpl = friendServiceImpl;

    }

    @PostMapping(path = "add/user")
    public void addUser(@RequestBody UserPostDto userPostDto) {
        userServiceImpl.addUser(userPostDto);
    }

    @PostMapping(path = "add/friend")
    public void addFriend(@RequestBody FriendPostDto friendPostDro) {
        friendServiceImpl.addFriend(friendPostDro);
    }

    @GetMapping(path="find/friend/{name}")
    public List<UserGetDto> findFriend(@PathVariable String name)
    {
        return friendServiceImpl.getFriendByFirstName(name);
    }

}
