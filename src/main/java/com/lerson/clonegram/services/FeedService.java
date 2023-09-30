package com.lerson.clonegram.services;

import com.lerson.clonegram.entities.FeedEntity;
import com.lerson.clonegram.models.Feed;
import com.lerson.clonegram.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class FeedService {

    @Value("${file.feed.upload-dir}")
    private String uploadDir;

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    MultipartFileHandler multipartFileHandler;

    public Feed createFeed(FeedEntity feedEntity) throws IOException {
        String userAvatarName = this.multipartFileHandler.saveFile(feedEntity.getUserAvatar());
        String feedImageName = this.multipartFileHandler.saveFile(feedEntity.getImage());

        String userAvatarUrl = String.format("%s/avatars/%s", this.uploadDir, userAvatarName);
        String feedImageUrl = String.format("%s/images/%s", this.uploadDir, feedImageName);

        String postedAgo = new SimpleDateFormat("dd/MM/yyyy").format(feedEntity.getPostedAgo());

        return this.feedRepository.save(new Feed(
                feedEntity.getUserNickName(), userAvatarUrl, feedEntity.getLocalName(), feedImageUrl,
                feedEntity.getDescription(), postedAgo, feedEntity.getContLikes(),
                feedEntity.getCommentLikes()
        ));
    }

    public List<Feed> findAllFeeds() {

        return this.feedRepository.findAll();
    }
}
