package com.lerson.clonegram.controllers;

import com.lerson.clonegram.dto.StoryDTO;
import com.lerson.clonegram.entities.StoryEntity;
import com.lerson.clonegram.services.StoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/stories")
public class StoryController {

    private final StoryService storyService;

    @Autowired
    public StoryController(StoryService storyService) {

        this.storyService = storyService;
    }

    @Operation(
            summary = "Create a story",
            description = "Create a story by providing a nickname and an avatar image. " +
                    "Returns the info from the created story."
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StoryDTO> createStory(
            @Parameter(
                    description = "Avatar image to be uploaded",
                    content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE)
            )
            @RequestPart("userAvatar") MultipartFile userAvatar,

            @Parameter(description = "User nickname")
            @RequestPart("userNickName") String userNickName) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.storyService.createStory(new StoryEntity(userNickName, userAvatar)));
    }

    @Operation(
            summary = "Find all stories",
            description = "Return a list with all created stories in."
    )
    @GetMapping
    public ResponseEntity<List<StoryDTO>> findAllStories() {

        return ResponseEntity.ok(this.storyService.findAllStories());
    }
}
