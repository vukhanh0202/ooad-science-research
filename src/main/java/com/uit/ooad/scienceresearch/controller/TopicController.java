package com.uit.ooad.scienceresearch.controller;

import com.uit.ooad.scienceresearch.constant.DefaultConstant;
import com.uit.ooad.scienceresearch.payload.ApiResponse;
import com.uit.ooad.scienceresearch.dto.topic.TopicDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicFullDto;
import com.uit.ooad.scienceresearch.payload.PaginationResponse;
import com.uit.ooad.scienceresearch.service.topic.IFindAllTopicService;
import com.uit.ooad.scienceresearch.service.topic.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@RestController
@RequestMapping(value = "/topic")
public class TopicController {

    @Autowired
    ITopicService topicService;

    /**
     * Find All Topic
     *
     * @return
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTopic(@RequestParam(value = "page", defaultValue = DefaultConstant.PAGE_NUMBER_DEFAULT) Integer page,
                                         @RequestParam(value = "size", defaultValue = DefaultConstant.PAGE_SIZE_DEFAULT) Integer size) {
        List<TopicFullDto> result = topicService.getFindAllTopicService().execute(new IFindAllTopicService.Input(page, size));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PaginationResponse(Integer.parseInt(topicService.getCountTopicService().execute().toString())
                        , size, page, result));
    }

    /**
     * Find Topic with Id
     *
     * @return
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTopicById(@PathVariable("id") Long topicId) {
        TopicFullDto result = topicService.getFindTopicByIdService().execute(topicId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(result));
    }


    /**
     * Add new topic
     *
     * @param body
     * @return
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addTopic(@RequestBody TopicDto body) {
        Boolean res = topicService.getAddTopicService().execute(body);
        if (res) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(res, "Success!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(res, "Fail!"));
        }
    }

    /**
     * Update topic
     *
     * @param body
     * @return
     */
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTopic(@RequestBody TopicDto body) {
        Boolean res = topicService.getUpdateTopicService().execute(body);
        if (res) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(res, "Success!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(res, "Fail!"));
        }
    }

    /**
     * Delete topic
     *
     * @param body
     * @return
     */
    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteTopic(@RequestBody TopicDto body) {
        Boolean res = topicService.getDeleteTopicService().execute(body);
        if (res) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(res, "Success!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(res, "Fail!"));
        }
    }
}
