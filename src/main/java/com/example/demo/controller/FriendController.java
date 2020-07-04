package com.example.demo.controller;

import com.example.demo.dto.FriendGetDto;
import com.example.demo.service.FriendServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("${client.url}")
public class FriendController {


    private FriendServiceImpl friendServiceImpl;

    public FriendController(FriendServiceImpl friendServiceImpl) {
        this.friendServiceImpl = friendServiceImpl;
    }

    @GetMapping(path = "/get/friends/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    List<FriendGetDto> getFriend(@PathVariable String userId) {

        return friendServiceImpl.getFriend(userId);
    }
}
