package com.example.demo.dto;

public class MessageGetDto {

    private String textMessage;
    private String date;
    private String userId;
    private long friendId;

    public MessageGetDto() {
    }

    public MessageGetDto(String textMessage, String date, String userId) {
        this.textMessage = textMessage;
        this.date = date;
        this.userId = userId;
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

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
