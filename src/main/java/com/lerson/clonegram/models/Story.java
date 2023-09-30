package com.lerson.clonegram.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("story")
public class Story extends User {

    @Id
    private ObjectId id;

    public Story() {
        super();
    }

    public Story(String userNickName, String userAvatar) {
        super(userNickName, userAvatar);
    }
}
