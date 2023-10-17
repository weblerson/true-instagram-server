package com.lerson.clonegram;

import com.lerson.clonegram.entities.StoryEntity;
import com.lerson.clonegram.exceptions.DBException;
import com.lerson.clonegram.repository.StoryRepository;
import com.lerson.clonegram.services.StorageService;
import com.lerson.clonegram.services.StoryService;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
public class StoryMockTests {

    private static StoryEntity storyEntity;

    @Mock
    private StorageService storageService;

    @Mock
    private StoryRepository storyRepository;

    @Mock
    private StoryService storyService;

    private static MultipartFile createMultipartFile(String fileName) {

        return new MockMultipartFile(
                fileName, fileName.concat(".jpeg"), MediaType.IMAGE_JPEG_VALUE, new byte[]{1,2,3,5}
        );
    }

    @BeforeAll
    public static void setUpTestData() {

        MultipartFile userAvatar = createMultipartFile("userAvatar");
        storyEntity = new StoryEntity("testNickName", userAvatar);
    }

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
        this.storyService = new StoryService(storyRepository, storageService);
    }

    @Test
    void testIfStoryServiceCreatStoryThrowsDBException() {

        Mockito.doThrow(new IllegalArgumentException("Simulated IllegalArgumentException"))
                .when(this.storyRepository).save(Mockito.any());

        assertThrows(DBException.class, () -> this.storyService.createStory(storyEntity));
    }

    @Test
    void testIfStoryServiceFindAllStoriesThrowsDBException() {

        Mockito.doThrow(new IllegalArgumentException("Simulated IllegalArgumentException"))
                .when(this.storyRepository).findAll();

        assertThrows(DBException.class, () -> this.storyService.findAllStories());
    }
}
