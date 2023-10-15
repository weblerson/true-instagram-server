package com.lerson.clonegram.services;

import com.lerson.clonegram.dto.StoryDTO;
import com.lerson.clonegram.entities.StoryEntity;
import com.lerson.clonegram.models.Story;
import com.lerson.clonegram.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StoryService {

    private final StoryRepository storyRepository;
    private final StorageService storageService;

    @Value("${file.story.upload-dir}")
    private String uploadDir;

    @Autowired
    public StoryService(StoryRepository storyRepository, StorageService storageService) {

        this.storyRepository = storyRepository;
        this.storageService = storageService;
    }

    public StoryDTO createStory(StoryEntity storyEntity) throws IOException {
        String fileName = this.storageService.saveFile(storyEntity.getUserAvatar(), uploadDir);
        String imageUrl = String.format("/media/stories/%s", fileName);

        Story createdStory = storyRepository.save(new Story(storyEntity.getUserNickName(), imageUrl));

        return new StoryDTO(createdStory);
    }

    public List<StoryDTO> findAllStories() {

        List<Story> stories = this.storyRepository.findAll();

        return stories.stream().map(StoryDTO::new).toList();
    }
}
