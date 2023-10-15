package com.lerson.clonegram;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lerson.clonegram.dto.FeedMinDTO;
import com.lerson.clonegram.models.Feed;
import com.lerson.clonegram.repository.FeedRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FeedTests {

    private final FeedRepository feedRepository;
    private final MockMvc mockMvc;

    @Autowired
    public FeedTests(FeedRepository feedRepository, MockMvc mockMvc) {

        this.feedRepository = feedRepository;
        this.mockMvc = mockMvc;
    }

    private static Feed feed;
    private static MultiValueMap<String, String> params;

    private static MockMultipartFile createTestJpeg(String param) {

        return new MockMultipartFile(
                param, param.concat(".jpeg"), MediaType.IMAGE_JPEG_VALUE, new byte[]{1,2,3,5}
        );
    }

    private String createFeedAndGetId() throws Exception {
        MockMultipartFile userAvatar = createTestJpeg("userAvatar");
        MockMultipartFile image = createTestJpeg("image");

        MvcResult result = this.mockMvc.perform(
                        multipart("/feeds")
                                .file(userAvatar)
                                .file(image)
                                .params(params))
                .andExpect(status().isCreated())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        ObjectMapper obj = new ObjectMapper();

        FeedMinDTO feed = obj.readValue(jsonResponse, FeedMinDTO.class);
        return feed.getId();
    }

    @BeforeAll
    static void setUp() {

        feed = new Feed();
        feed.setUserNickName("testUser");
        feed.setUserAvatar("testUrl");
        feed.setLocalName("testLocalName");
        feed.setImageUrl("testImageUrl");
        feed.setDescription("testDescription");
        feed.setPostedAgo("00/00/0000");
        feed.setContLikes(1);
        feed.setCommentLikes(1);

        params = new LinkedMultiValueMap<>();
        params.add("userNickName", "testNickName");
        params.add("localName", "testLocalName");
        params.add("description", "testDescription");
        params.add("postedDate", "00/00/0000");
    }

    @Test
    void testFeedCreation() {

        Feed created = this.feedRepository.save(feed);

        assertNotNull(created);
        assertEquals(feed, created);
    }

    @Test
    void testIfCreateFeedReturnsHttpCreated() throws Exception {

        MockMultipartFile userAvatar = createTestJpeg("userAvatar");
        MockMultipartFile image = createTestJpeg("image");

        this.mockMvc.perform(
                multipart("/feeds")
                        .file(userAvatar)
                        .file(image)
                        .params(params))
                .andExpect(status().isCreated());
    }

    @Test
    void testIfFindAllFeedsReturnsHttpOk() throws Exception {

        this.mockMvc.perform(
                get("/feeds")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testIfIncrementReturnsHttpOkWhenEntityExists() throws Exception {

        String id = this.createFeedAndGetId();

        this.mockMvc.perform(
                patch("/feeds/".concat(id))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("contLikes", "true")
                        .queryParam("commentLikes", "true"))
                .andExpect(status().isOk());
    }

    @Test
    void testIfIncrementReturnsHttpNoContentWhenEntityNotExists() throws Exception {

        String id = UUID.randomUUID().toString();

        this.mockMvc.perform(
                patch("/feeds/".concat(id))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("contLikes", "true")
                        .queryParam("commentLikes", "true"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testIfIncrementIsIncrementingContLikes() throws Exception {

        String id = this.createFeedAndGetId();
        Optional<Feed> before = this.feedRepository.findById(id);

        this.mockMvc.perform(
                patch("/feeds/".concat(id))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("contLikes", "true"))
                .andExpect(status().isOk());

        Optional<Feed> after = this.feedRepository.findById(id);

        if (before.isPresent() && after.isPresent())
            assertEquals(before.get().getContLikes() + 1, after.get().getContLikes());
    }

    @Test
    void testIfIncrementIsIncrementingCommentLikes() throws Exception {

        String id = this.createFeedAndGetId();
        Optional<Feed> before = this.feedRepository.findById(id);

        this.mockMvc.perform(
                        patch("/feeds/".concat(id))
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .queryParam("commentLikes", "true"))
                .andExpect(status().isOk());

        Optional<Feed> after = this.feedRepository.findById(id);

        if (before.isPresent() && after.isPresent())
            assertEquals(before.get().getCommentLikes() + 1, after.get().getCommentLikes());
    }

    @Test
    void testIfIncrementIsIncrementingBothContLikesAndCommentLikes() throws Exception {

        String id = this.createFeedAndGetId();
        Optional<Feed> before = this.feedRepository.findById(id);

        this.mockMvc.perform(
                        patch("/feeds/".concat(id))
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .queryParam("contLikes", "true")
                                .queryParam("commentLikes", "true"))
                .andExpect(status().isOk());

        Optional<Feed> after = this.feedRepository.findById(id);

        if (before.isPresent() && after.isPresent()) {
            assertEquals(before.get().getContLikes() + 1, after.get().getContLikes());
            assertEquals(before.get().getCommentLikes() + 1, after.get().getCommentLikes());
        }
    }

    @Test
    void testIfIncrementReturnsHttpBadRequestIfNoQueryParamIsGiven() throws Exception {

        String id = this.createFeedAndGetId();

        this.mockMvc.perform(
                patch("/feeds/".concat(id))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
