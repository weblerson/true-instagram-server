package com.lerson.clonegram.repository;

import com.lerson.clonegram.models.Story;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoryRepository extends MongoRepository<Story, String> {
}
