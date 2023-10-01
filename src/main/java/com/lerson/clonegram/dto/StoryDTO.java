package com.lerson.clonegram.dto;

import com.lerson.clonegram.models.Story;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;

public class StoryDTO {

    private String id;
    private String userNickName;
    private String userAvatar;

    public StoryDTO() {

    }

    public StoryDTO(Story story) {
        BeanUtils.copyProperties(story, this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
