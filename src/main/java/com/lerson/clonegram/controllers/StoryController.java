package com.lerson.clonegram.controllers;

import com.lerson.clonegram.dto.StoryDTO;
import com.lerson.clonegram.entities.StoryEntity;
import com.lerson.clonegram.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/stories")
public class StoryController {

    @Autowired
    StoryService storyService;

    @PostMapping
    public ResponseEntity<StoryDTO> createStory(@RequestParam("userAvatar") MultipartFile userAvatar,
                                      @RequestParam("userNickName") String userNickName) throws IOException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.storyService.createStory(new StoryEntity(userNickName, userAvatar)));
    }

    @GetMapping
    public ResponseEntity<List<StoryDTO>> findAllStories() {

        return ResponseEntity.ok(this.storyService.findAllStories());
    }
}
