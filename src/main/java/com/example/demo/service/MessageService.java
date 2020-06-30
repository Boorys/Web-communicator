package com.example.demo.service;

import com.example.demo.dto.FriendGetDto;
import com.example.demo.dto.MessageGetDto;
import com.example.demo.dto.MessagePostDto;

import com.example.demo.entity.FriendEntity;
import com.example.demo.entity.MessageEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.FriendRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MessageService {

    private static final int PAGE_SIZE = 10;
    MessageRepository messageRepository;
    FriendRepository friendRepository;
    UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, FriendRepository friendRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
    }

    public void addMessage(MessagePostDto messagePostDto) {

        FriendEntity friendEntity = friendRepository.findFriendEntityById(messagePostDto.getFriendId());
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setTextMessage(messagePostDto.getTextMessage());
        messageEntity.setUserId(messagePostDto.getUserId());
        friendEntity.getMessageEntityList().add(messageEntity);
        messageEntity.setFriend(friendEntity);
        messageRepository.save(messageEntity);
        friendRepository.save(friendEntity);

    }

    public List<MessageGetDto> getMessage(int numberMessages,String friendId)
    {
        List<Object[]> messageGetDto = messageRepository.getMessageByFriendId(friendId,numberMessages);

        return messageGetDto.stream().map(x->new MessageGetDto(x[0].toString(),x[1].toString(),x[2].toString())).collect(Collectors.toList());
    }

}
