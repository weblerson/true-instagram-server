package com.lerson.clonegram.entities;

import org.springframework.web.multipart.MultipartFile;

public class StoryEntity {

    private String userNickName;
    private MultipartFile userAvatar;

    public StoryEntity(String userNickName, MultipartFile userAvatar) {
        this.userNickName = userNickName;
        this.userAvatar = userAvatar;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public MultipartFile getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(MultipartFile userAvatar) {
        this.userAvatar = userAvatar;
    }
}
