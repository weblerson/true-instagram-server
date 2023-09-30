package com.lerson.clonegram;

import com.lerson.clonegram.entities.StoryEntity;
import com.lerson.clonegram.models.Story;
import com.lerson.clonegram.repository.StoryRepository;
import com.lerson.clonegram.services.StoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class StoryServiceTests {

    @InjectMocks
    StoryService storyService;

    @Mock
    StoryRepository storyRepository;

    @Mock
    MultipartFile multipartFile;

    @Value("${file.story.upload-dir}")
    private String uploadDir;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateStory() throws IOException {

        when(multipartFile.getOriginalFilename()).thenReturn("example.jpg");

        Story savedStory = new Story("testuser", "example.jpg");
        when(storyRepository.save(any(Story.class))).thenReturn(savedStory);

        storyService.createStory(new StoryEntity("testuser", multipartFile));
        verify(storyRepository, times(1)).save(any(Story.class));
    }
}
