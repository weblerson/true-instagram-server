package com.lerson.clonegram.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("feed")
public class Feed extends User {

    @Id
    private String id;
    private String localName;
    private String imageUrl;
    private String description;
    private String postedAgo;
    private Integer contLikes;
    private Integer commentLikes;

    public Feed() {
        super();
    }

    public Feed(String userNickName, String userAvatar, String id, String localName, String imageUrl,
                String description, String postedAgo, Integer contLikes, Integer commentLikes) {
        super(userNickName, userAvatar);
        this.id = id;
        this.localName = localName;
        this.imageUrl = imageUrl;
        this.description = description;
        this.postedAgo = postedAgo;
        this.contLikes = contLikes;
        this.commentLikes = commentLikes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
