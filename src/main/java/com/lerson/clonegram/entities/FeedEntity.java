package com.lerson.clonegram.entities;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class FeedEntity {

    private String userNickName;
    private MultipartFile userAvatar;
    private String localName;
    private MultipartFile image;
    private String description;
    private Date postedAgo;
    private Integer contLikes;
    private Integer commentLikes;

    public FeedEntity(String userNickName, MultipartFile userAvatar, String localName, MultipartFile image,
                      String description, Date postedAgo) {
        this.userNickName = userNickName;
        this.userAvatar = userAvatar;
        this.localName = localName;
        this.image = image;
        this.description = description;
        this.postedAgo = postedAgo;
        this.contLikes = 0;
        this.commentLikes = 0;
    }

    public FeedEntity(String userNickName, MultipartFile userAvatar, String localName, MultipartFile image,
                      String description, Date postedAgo, Integer contLikes, Integer commentLikes) {
        this.userNickName = userNickName;
        this.userAvatar = userAvatar;
        this.localName = localName;
        this.image = image;
        this.description = description;
        this.postedAgo = postedAgo;
        this.contLikes = contLikes;
        this.commentLikes = commentLikes;
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

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPostedAgo() {
        return postedAgo;
    }

    public void setPostedAgo(Date postedAgo) {
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
