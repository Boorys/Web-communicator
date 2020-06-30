package com.example.demo.service;

import com.example.demo.dto.FriendGetDto;
import com.example.demo.dto.FriendPostDto;
import com.example.demo.dto.UserGetDto;
import com.example.demo.entity.FriendEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.FriendRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class FriendService {

    UserMapper userMapper;
    UserRepository userRepository;
    FriendRepository friendRepository;

    public FriendService(UserMapper userMapper, UserRepository userRepository, FriendRepository friendRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }

    public void addFriend(FriendPostDto friendPostDto) {

        UserEntity myUserEntity = userRepository.findByUserId(friendPostDto.getMyId());
        UserEntity frendUserEntity = userRepository.findByUserId(friendPostDto.getFriendId());

        FriendEntity friendEntity = new FriendEntity();
        friendEntity.setFriend(frendUserEntity);
        friendEntity.setOwner(myUserEntity);
        friendRepository.save(friendEntity);
    }

     public List getFriend(String userId) {

     List<Object[]> friendGetDtoList = friendRepository.findFriendGetDtoByUserId(userId);

       return friendGetDtoList.stream().map(x->new FriendGetDto(x[0].toString(),Long.parseLong(x[1].toString()),x[2].toString())).collect(Collectors.toList());
    }

    public List getFriendByFirstName(String firstName)
    {
        List<Object[]> friendGetDtoList = friendRepository.findByFirstName(firstName);
        return friendGetDtoList.stream().map(x->new UserGetDto(x[1].toString(),x[0].toString())).collect(Collectors.toList());
    }

}
