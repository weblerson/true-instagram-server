package com.lerson.clonegram.models;

public abstract class User {

    private String userNickName;
    private String userAvatar;

    public User() {

    }

    public User(String userNickName, String userAvatar) {
        this.userNickName = userNickName;
        this.userAvatar = userAvatar;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}
