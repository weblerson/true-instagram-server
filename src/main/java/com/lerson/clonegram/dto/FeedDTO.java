package com.lerson.clonegram.dto;

import com.lerson.clonegram.models.Feed;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;

public class FeedDTO {

    private String id;
    private String userNickName;
    private String userAvatar;
    private String localName;
    private String imageUrl;
    private String description;
    private String postedAgo;
    private Integer contLikes;
    private Integer commentLikes;

    public FeedDTO() {

    }

    public FeedDTO(Feed feed) {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostedAgo() {
        return postedAgo;
    }

    public void setPostedAgo(String postedAgo) {
        this.postedAgo = postedAgo;
    }

    public Integer getContLikes() {
        return contLikes;
    }

    public void setContLikes(Integer contLikes) {
        this.contLikes = contLikes;
    }

    public Integer getCommentLikes() {
        return commentLikes;
    }

    public void setCommentLikes(Integer commentLikes) {
        this.commentLikes = commentLikes;
    }
}
