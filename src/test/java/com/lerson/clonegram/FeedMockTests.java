package com.lerson.clonegram;

import com.lerson.clonegram.entities.FeedEntity;
import com.lerson.clonegram.exceptions.DBException;
import com.lerson.clonegram.models.Feed;
import com.lerson.clonegram.repository.FeedRepository;
import com.lerson.clonegram.services.FeedService;
import com.lerson.clonegram.services.StorageService;
import com.lerson.clonegram.util.TimeUtil;
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

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FeedMockTests {

    private static FeedEntity feedEntity;
    private static Feed feed;
    private static String id;

    @Mock
    private StorageService storageService;

    @Mock
    private FeedService feedService;

    @Mock
    private FeedRepository feedRepository;

    private static MultipartFile createMultipartFile(String fileName) {

        return new MockMultipartFile(
                fileName, fileName.concat(".jpeg"), MediaType.IMAGE_JPEG_VALUE, new byte[]{1,2,3,5}
        );
    }

    @BeforeAll
    static void setUpTestData() {

        id = UUID.randomUUID().toString();

        MultipartFile userAvatar = createMultipartFile("userAvatar");
        MultipartFile image = createMultipartFile("image");

        Date postedAgo = TimeUtil.parseDateOfString("00/00/0000");

        feedEntity = new FeedEntity(
                "testNickName",
                userAvatar,
                "testLocalName",
                image,
                "testDescription",
                postedAgo
        );

        feed = new Feed();
        feed.setUserNickName("testUser");
        feed.setUserAvatar("testUrl");
        feed.setLocalName("testLocalName");
        feed.setImageUrl("testImageUrl");
        feed.setDescription("testDescription");
        feed.setPostedAgo("00/00/0000");
        feed.setContLikes(1);
        feed.setCommentLikes(1);
    }

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
        feedService = new FeedService(feedRepository, storageService);
    }

    @Test
    void testIfFeedServiceCreateFeedThrowsDBException() {

        Mockito.doThrow(new IllegalArgumentException("Simulated IllegalArgumentException"))
                .when(this.feedRepository).save(Mockito.any());

        assertThrows(DBException.class, () -> this.feedService.createFeed(feedEntity));
    }

    @Test
    void testIfFeedServiceFindByIdMinThrowsDBException() {

        Mockito.doThrow(new IllegalArgumentException("Simulated IllegalArgumentException"))
                .when(this.feedRepository).findById(Mockito.any());

        assertThrows(DBException.class, () -> this.feedService.findByIdMin(id));
    }

    @Test
    void testIfFeedServiceFindAllFeedsThrowsDBException() {

        Mockito.doThrow(new IllegalArgumentException("Simulated IllegalArgumentException"))
                .when(this.feedRepository).findAll();

        assertThrows(DBException.class, () -> this.feedService.findAllFeeds());
    }

    @Test
    void testIfFeedServiceIncrementContLikesFindByIdThrowsDBException() {

        Mockito.doThrow(new IllegalArgumentException("Simulated IllegalArgumentException"))
                .when(this.feedRepository).findById(id);

        assertThrows(DBException.class, () -> this.feedService.incrementContLikes(id));
    }

    @Test
    void testIfFeedServiceIncrementCommentLikesFindByIdThrowsDBException() {

        Mockito.doThrow(new IllegalArgumentException("Simulated IllegalArgumentException"))
                .when(this.feedRepository).findById(id);

        assertThrows(DBException.class, () -> this.feedService.incrementCommentLikes(id));
    }

    @Test
    void testIfFeedServiceIncrementContLikesSaveThrowsDBException() {

        Mockito.when(this.feedRepository.findById(id)).thenReturn(Optional.of(feed));
        Mockito.doThrow(new IllegalArgumentException("Simulated IllegalArgumentException"))
                .when(this.feedRepository).save(Mockito.any());

        assertThrows(DBException.class, () -> this.feedService.incrementContLikes(id));
    }

    @Test
    void testIfFeedServiceIncrementCommentLikesSaveThrowsDBException() {

        Mockito.when(this.feedRepository.findById(id)).thenReturn(Optional.of(feed));
        Mockito.doThrow(new IllegalArgumentException("Simulated IllegalArgumentException"))
                .when(this.feedRepository).save(Mockito.any());

        assertThrows(DBException.class, () -> this.feedService.incrementCommentLikes(id));
    }
}
