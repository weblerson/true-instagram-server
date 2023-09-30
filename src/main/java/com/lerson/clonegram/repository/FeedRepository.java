package com.lerson.clonegram.repository;

import com.lerson.clonegram.models.Feed;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedRepository extends MongoRepository<Feed, String> {
}
