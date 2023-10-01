package com.lerson.clonegram.dto;

import com.lerson.clonegram.models.Feed;
import org.springframework.beans.BeanUtils;

public class FeedMinDTO {

    private String id;
    private String userNickName;
    private String userAvatar;
    private String localName;
    private String imageUrl;

    public FeedMinDTO() {

    }

    public FeedMinDTO(Feed feed) {

        BeanUtils.copyProperties(feed, this);
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

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
