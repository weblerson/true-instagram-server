package com.lerson.clonegram;

import com.lerson.clonegram.models.Story;
import com.lerson.clonegram.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StoryTests {

    private final StoryRepository storyRepository;
    private final MockMvc mockMvc;

    @Autowired
    public StoryTests(StoryRepository storyRepository, MockMvc mockMvc) {

        this.storyRepository = storyRepository;
        this.mockMvc = mockMvc;
    }

    private Optional<Story> createStoryAndGetById(Story story) {

        Story created = this.storyRepository.save(story);
        return this.storyRepository.findById(created.getId());
    }

    @Test
    void testStoryCreation() {

        Story story = new Story();
        story.setUserNickName("userTest");
        story.setUserAvatar("testUrl");

        Optional<Story> opt = this.createStoryAndGetById(story);

        assertTrue(opt.isPresent());
    }

    @Test
    void testIfStoryUserNicknameMatches() {

        String nickname = "assertNickname";
        Story story = new Story();
        story.setUserNickName(nickname);
        story.setUserAvatar("testUrl");

        Optional<Story> opt = this.createStoryAndGetById(story);

        opt.ifPresent( (Story p) -> assertEquals(nickname, p.getUserNickName()) );
    }

    @Test
    void testIfStoryUserAvatarMatches() {

        String avatar = "assertAvatar";
        Story story = new Story();
        story.setUserNickName("userTest");
        story.setUserAvatar(avatar);

        Optional<Story> opt = this.createStoryAndGetById(story);

        opt.ifPresent( (Story p) -> assertEquals(avatar, p.getUserAvatar()));
    }

    @Test
    void testIfCreateStoryReturnsCreated() throws Exception {

        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "userAvatar", "file.jpeg", MediaType.IMAGE_JPEG_VALUE, new byte[]{1,2,3,4,5});

        MockMultipartFile json = new MockMultipartFile(
                "userNickName", null, MediaType.APPLICATION_JSON_VALUE,
                "{\"userNickName\": \"testNickName\"}".getBytes()
        );

        this.mockMvc.perform(
                MockMvcRequestBuilders.multipart("/stories")
                        .file(mockMultipartFile)
                        .file(json))
                        .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testIfFindAllStoriesReturnsOk() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/stories")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
