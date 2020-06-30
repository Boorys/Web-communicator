package com.example.demo.controller;

import com.example.demo.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin
public class FriendController {

    @Autowired
    FriendService friendService;

    @GetMapping(path = "/get/friends/{userId}")
    List getFriend(@PathVariable String userId) {

        return friendService.getFriend(userId);
    }
}
