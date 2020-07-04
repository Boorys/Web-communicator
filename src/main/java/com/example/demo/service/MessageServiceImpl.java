package com.example.demo.service;

import com.example.demo.dto.MessageGetDto;
import com.example.demo.dto.MessagePostDto;
import com.example.demo.entity.FriendEntity;
import com.example.demo.entity.MessageEntity;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.FriendRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MessageServiceImpl {

    private MessageRepository messageRepository;
    private FriendRepository friendRepository;
    private UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository, FriendRepository friendRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
    }

    public void addMessage(MessagePostDto messagePostDto) {

        FriendEntity friendEntity = Optional.ofNullable(friendRepository.findFriendEntityById(messagePostDto.getFriendId()))
                .orElseThrow(UserNotFoundException::new);
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
