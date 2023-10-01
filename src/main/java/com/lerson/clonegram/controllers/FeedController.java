package com.lerson.clonegram.controllers;

import com.lerson.clonegram.dto.FeedDTO;
import com.lerson.clonegram.dto.FeedMinDTO;
import com.lerson.clonegram.entities.FeedEntity;
import com.lerson.clonegram.services.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/feeds")
public class FeedController {

    private final FeedService feedService;

    @Autowired
    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @PostMapping
    public ResponseEntity<FeedMinDTO> createFeed(
            @RequestParam("userNickName") String userNickName, @RequestParam("userAvatar") MultipartFile userAvatar,
            @RequestParam("localName") String localName, @RequestParam("image") MultipartFile image,
            @RequestParam("description") String description, @RequestParam("postedDate") String postedDate)
            throws IOException, ParseException {

        Date postedAgo = new SimpleDateFormat("dd/MM/yyyy").parse(postedDate);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.feedService.createFeed(new FeedEntity(
                        userNickName, userAvatar, localName, image, description, postedAgo
                )));
    }

    @GetMapping
    public ResponseEntity<List<FeedDTO>> findAllFeeds() {

        return ResponseEntity.ok(this.feedService.findAllFeeds());
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<FeedMinDTO> increment(
            @PathVariable String id,
            @RequestParam(name = "contLikes", required = false, defaultValue = "false") Boolean contLikes,
            @RequestParam(name = "commentLikes", required = false, defaultValue = "false") Boolean commentLikes) {

        Optional<FeedMinDTO> opt = this.feedService.findByIdMin(id);

        if (contLikes)
            this.feedService.incrementContLikes(id);

        if (commentLikes)
            this.feedService.incrementCommentLikes(id);

        return opt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new FeedMinDTO()));
    }
}
