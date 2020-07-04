package com.example.demo.service;

import com.example.demo.dto.FriendGetDto;
import com.example.demo.dto.FriendPostDto;
import com.example.demo.dto.UserGetDto;
import com.example.demo.entity.FriendEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.FriendRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendServiceImpl {

    private UserMapper userMapper;
    private UserRepository userRepository;
    private FriendRepository friendRepository;

    public FriendServiceImpl(UserMapper userMapper, UserRepository userRepository, FriendRepository friendRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }

    public void addFriend(FriendPostDto friendPostDto) {

        UserEntity myUserEntity = Optional.ofNullable(userRepository.findByUserId(friendPostDto.getMyId())).orElseThrow(UserNotFoundException::new);
        UserEntity friendUserEntity = Optional.ofNullable(userRepository.findByUserId(friendPostDto.getFriendId())).orElseThrow(UserNotFoundException::new);

        FriendEntity friendEntity = new FriendEntity();
        friendEntity.setFriend(friendUserEntity);
        friendEntity.setOwner(myUserEntity);
        friendRepository.save(friendEntity);
    }

    public List<FriendGetDto> getFriend(String userId) {

        List<Object[]> objectsList = friendRepository.findFriendGetDtoByUserId(userId);
        List<FriendGetDto> friendGetDtoList;
        try {

            friendGetDtoList = objectsList.stream().map(x -> new FriendGetDto(x[0].toString(), Long.parseLong(x[1].toString()), x[2].toString())).collect(Collectors.toList());
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
        return friendGetDtoList;
    }

    public List<UserGetDto> getFriendByFirstName(String firstName) {
        List<Object[]> friendGetDtoList = Optional.ofNullable(friendRepository.findByFirstName(firstName))
                .orElseThrow(UserNotFoundException::new);
        return friendGetDtoList.stream().map(x -> new UserGetDto(x[1].toString(), x[0].toString())).collect(Collectors.toList());
    }

}
