package com.lerson.clonegram.controllers;

import com.lerson.clonegram.dto.FeedDTO;
import com.lerson.clonegram.dto.FeedMinDTO;
import com.lerson.clonegram.entities.FeedEntity;
import com.lerson.clonegram.exceptions.AllQueryParamsMissingException;
import com.lerson.clonegram.services.FeedService;
import com.lerson.clonegram.util.TimeUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Operation(
            summary = "Create Feed",
            description = "Create an feed post. Returns the min info about the created feed."
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FeedMinDTO> createFeed(
            @RequestPart("userNickName")
            @Parameter(description = "User Nickname.", required = true)
            String userNickName,

            @RequestPart("localName")
            @Parameter(description = "Local Name", required = true)
            String localName,

            @RequestPart("description")
            @Parameter(description = "Feed Description", required = true)
            String description,

            @RequestPart("postedDate")
            @Parameter(description = "Feed Posted Date", required = true)
            String postedDate,

            @RequestPart("userAvatar")
            @Parameter(
                    description = "Avatar image to be uploaded.",
                    content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE),
                    required = true
            )
            MultipartFile userAvatar,

            @RequestPart("image")
            @Parameter(
                    description = "Feed image to be uploaded.",
                    content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE),
                    required = true
            )
            MultipartFile image) {

        Date postedAgo = TimeUtil.parseDateOfString(postedDate);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.feedService.createFeed(new FeedEntity(
                        userNickName, userAvatar, localName,
                        image, description, postedAgo
                )));
    }

    @Operation(
            summary = "Find all feeds.",
            description = "Find all created feed posts. Returns a list of feeds."
    )
    @GetMapping
    public ResponseEntity<List<FeedDTO>> findAllFeeds() {

        return ResponseEntity.ok(this.feedService.findAllFeeds());
    }

    @Operation(
            summary = "Increment",
            description = "Increment feed's likes or comments count. Return the min info of the incremented feed."
    )
    @PatchMapping(value = "/{id}")
    public ResponseEntity<FeedMinDTO> increment(
            @PathVariable
            @Parameter(description = "Id of an existing story", required = true)
            String id,

            @RequestParam(name = "contLikes", required = false, defaultValue = "false")
            @Parameter(description = "Want to increment the like's count?")
            Boolean contLikes,

            @RequestParam(name = "commentLikes", required = false, defaultValue = "false")
            @Parameter(description = "Want to increment the comment's count?")
            Boolean commentLikes) {

        Optional<FeedMinDTO> opt = this.feedService.findByIdMin(id);

        if (contLikes)
            this.feedService.incrementContLikes(id);

        if (commentLikes)
            this.feedService.incrementCommentLikes(id);

        if (! contLikes && ! commentLikes)
            throw new AllQueryParamsMissingException("Missing all query params.");

        return opt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new FeedMinDTO()));
    }
}
