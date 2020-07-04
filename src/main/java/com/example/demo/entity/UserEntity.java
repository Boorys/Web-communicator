package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String firstName;

    private String password;

    private String email;

    @OneToMany(mappedBy = "owner")
    private List<FriendEntity> owner = new ArrayList<>();

    @OneToMany(mappedBy = "friend")
    private List<FriendEntity> friend = new ArrayList<>();


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FriendEntity> getOwner() {
        return owner;
    }

    public void setOwner(List<FriendEntity> owner) {
        this.owner = owner;
    }

    public List<FriendEntity> getFriend() {
        return friend;
    }

    public void setFriend(List<FriendEntity> friend) {
        this.friend = friend;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
