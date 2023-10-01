package com.lerson.clonegram.services;

import com.lerson.clonegram.dto.StoryDTO;
import com.lerson.clonegram.entities.StoryEntity;
import com.lerson.clonegram.models.Story;
import com.lerson.clonegram.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class StoryService {

    @Value("${file.story.upload-dir}")
    private String uploadDir;

    @Autowired
    StoryRepository storyRepository;

    @Autowired
    MultipartFileHandler multipartFileHandler;

    public StoryDTO createStory(StoryEntity storyEntity) throws IOException {
        String fileName = this.multipartFileHandler.saveFile(storyEntity.getUserAvatar());
        String imageUrl = String.format("/media/stories/%s", fileName);

        Story createdStory = storyRepository.save(new Story(storyEntity.getUserNickName(), imageUrl));

        return new StoryDTO(createdStory);
    }

    public List<StoryDTO> findAllStories() {

        List<Story> stories = this.storyRepository.findAll();

        return stories.stream().map(StoryDTO::new).toList();
    }
}
