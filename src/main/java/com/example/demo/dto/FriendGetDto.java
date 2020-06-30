package com.example.demo.dto;

public class FriendGetDto {

    private String friendName;
    private long friendId;
    private String userId;

    public FriendGetDto() {
    }

    public FriendGetDto(String friendName, long friendId, String userId) {
        this.friendName = friendName;
        this.friendId = friendId;
        this.userId = userId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public long getFriendId() {
        return friendId;
    }

    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
