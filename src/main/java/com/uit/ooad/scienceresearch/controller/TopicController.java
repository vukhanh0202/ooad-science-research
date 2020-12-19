package com.uit.ooad.scienceresearch.controller;

import com.uit.ooad.scienceresearch.constant.DefaultConstant;
import com.uit.ooad.scienceresearch.data.UserPrincipal;
import com.uit.ooad.scienceresearch.dto.team.TeamLecturerDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicRegisterDto;
import com.uit.ooad.scienceresearch.payload.ApiResponse;
import com.uit.ooad.scienceresearch.dto.topic.TopicDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicFullDto;
import com.uit.ooad.scienceresearch.payload.PaginationResponse;
import com.uit.ooad.scienceresearch.service.team.IRegisterTopicService;
import com.uit.ooad.scienceresearch.service.team.ITeamService;
import com.uit.ooad.scienceresearch.service.topic.ICountTopicService;
import com.uit.ooad.scienceresearch.service.topic.IFindAllTopicRegisterService;
import com.uit.ooad.scienceresearch.service.topic.IFindAllTopicService;
import com.uit.ooad.scienceresearch.service.topic.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    ITeamService teamService;

    /**
     * Find All Topic
     *
     * @return
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTopic(@RequestParam(value = "page", defaultValue = DefaultConstant.PAGE_NUMBER_DEFAULT) Integer page,
                                         @RequestParam(value = "size", defaultValue = DefaultConstant.PAGE_SIZE_DEFAULT) Integer size,
                                         @RequestParam(value = "search", defaultValue = "") String search,
                                         @RequestParam(value = "facultyId", defaultValue = "") Long facultyId,
                                         @RequestParam(value = "levelId", defaultValue = "") Long levelId,
                                         @RequestParam(value = "fieldId", defaultValue = "") Long fieldId) {
        List<TopicFullDto> result = topicService
                .getFindAllTopicService()
                .execute(new IFindAllTopicService.Input(search, facultyId, levelId, fieldId, page, size));

        Long totalItem = topicService.getCountTopicService().execute(new ICountTopicService.Input(search, facultyId, levelId, fieldId));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PaginationResponse(Integer.parseInt(totalItem.toString())
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

    /**
     * Register topic by team
     *
     * @param body
     * @return
     */
    @PostMapping(value = "/register/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerTopic(@RequestBody List<TeamLecturerDto> body,
                                           @PathVariable("id") Long topicId) {
        Boolean res = teamService.getRegisterTopicService().execute(new IRegisterTopicService.Input(topicId, body));
        if (res) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(res, "Success!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(res, "Fail!"));
        }
    }

    /**
     * Find My topic
     *
     * @return
     */
    @GetMapping(value = "/my-topic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMyTopic(@RequestParam(value = "page", defaultValue = DefaultConstant.PAGE_NUMBER_DEFAULT) Integer page,
                                        @RequestParam(value = "size", defaultValue = DefaultConstant.PAGE_SIZE_DEFAULT) Integer size,
                                        @RequestParam(value = "search", defaultValue = "") String search) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<TopicRegisterDto> result = topicService
                .getFindAllTopicRegisterService()
                .execute(new IFindAllTopicRegisterService.Input(search, userPrincipal.getLecturerId(), page, size));
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    /**
     * Find My detail topic
     *
     * @return
     */
    @GetMapping(value = "/my-topic/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDetailMyTopic() {
           /*List<TopicRegisterDto> result = topicService
                .getFindAllTopicRegisterService()
                .execute(new IFindAllTopicRegisterService.Input(search, userPrincipal.getLecturerId(), page, size));
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);*/
           return null;
    }
}
