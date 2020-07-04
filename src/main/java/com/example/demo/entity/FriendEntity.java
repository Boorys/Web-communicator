package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class FriendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne()
    private UserEntity owner;

    @ManyToOne()
    private UserEntity friend;

    private boolean isActive;

    @OneToMany(mappedBy = "friend",fetch = FetchType.EAGER)
    private List<MessageEntity> messageEntityList = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public UserEntity getFriend() {
        return friend;
    }

    public void setFriend(UserEntity friend) {
        this.friend = friend;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<MessageEntity> getMessageEntityList() {
        return messageEntityList;
    }

    public void setMessageEntityList(List<MessageEntity> messageEntityList) {
        this.messageEntityList = messageEntityList;
    }
}
