package com.lerson.clonegram.services;

import com.lerson.clonegram.dto.FeedDTO;
import com.lerson.clonegram.dto.FeedMinDTO;
import com.lerson.clonegram.entities.FeedEntity;
import com.lerson.clonegram.models.Feed;
import com.lerson.clonegram.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class FeedService {

    private final FeedRepository feedRepository;
    private final StorageService storageService;

    @Value("${file.feed.upload-dir}")
    private String uploadDir;

    @Autowired
    public FeedService(FeedRepository feedRepository, StorageService storageService) {
        this.feedRepository = feedRepository;
        this.storageService = storageService;
    }

    public FeedMinDTO createFeed(FeedEntity feedEntity) throws IOException {
        String userAvatarName = this.storageService.saveFile(feedEntity.getUserAvatar(),
                String.format("%s/avatars", this.uploadDir));
        String feedImageName = this.storageService.saveFile(feedEntity.getImage(),
                String.format("%s/images", this.uploadDir));

        String userAvatarUrl = String.format("%s/avatars/%s", this.uploadDir, userAvatarName);
        String feedImageUrl = String.format("%s/images/%s", this.uploadDir, feedImageName);

        String postedAgo = new SimpleDateFormat("dd/MM/yyyy").format(feedEntity.getPostedAgo());

        Feed createdFeed = this.feedRepository.save(new Feed(
                feedEntity.getUserNickName(), userAvatarUrl, feedEntity.getLocalName(), feedImageUrl,
                feedEntity.getDescription(), postedAgo, feedEntity.getContLikes(),
                feedEntity.getCommentLikes()
        ));

        return new FeedMinDTO(createdFeed);
    }

    public Optional<FeedMinDTO> findByIdMin(String id) {

        Optional<Feed> opt = this.feedRepository.findById(id);

        return opt.map(FeedMinDTO::new);
    }

    public List<FeedDTO> findAllFeeds() {

        List<Feed> feeds = this.feedRepository.findAll();

        return feeds.stream().map(FeedDTO::new).toList();
    }

    public void incrementContLikes(String id) {

        Optional<Feed> opt = this.feedRepository.findById(id);
        opt.ifPresent((Feed feed) -> {
            feed.setContLikes(feed.getContLikes() + 1);
            this.feedRepository.save(feed);
        });
    }

    public void incrementCommentLikes(String id) {

        Optional<Feed> opt = this.feedRepository.findById(id);
        opt.ifPresent((Feed feed) -> {
            feed.setCommentLikes(feed.getCommentLikes() + 1);
            this.feedRepository.save(feed);
        });
    }
}
