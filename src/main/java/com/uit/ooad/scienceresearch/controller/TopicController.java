package com.uit.ooad.scienceresearch.controller;

import com.uit.ooad.scienceresearch.constant.DefaultConstant;
import com.uit.ooad.scienceresearch.constant.ERole;
import com.uit.ooad.scienceresearch.data.UserPrincipal;
import com.uit.ooad.scienceresearch.dto.team.TeamLecturerDto;
import com.uit.ooad.scienceresearch.dto.topic.*;
import com.uit.ooad.scienceresearch.payload.ApiResponse;
import com.uit.ooad.scienceresearch.payload.PaginationResponse;
import com.uit.ooad.scienceresearch.service.team.IRegisterTopicService;
import com.uit.ooad.scienceresearch.service.team.ITeamService;
import com.uit.ooad.scienceresearch.service.topic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @GetMapping(value = "/my-topic/{topicId}/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDetailMyTopic(@PathVariable("topicId") Long topicId, @PathVariable("teamId") Long teamId) {
        SignUpTopicFullDto result = topicService
                .getFindDetailTopicRegisterService()
                .execute(new IFindDetailTopicRegisterService.Input(topicId,teamId));
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    @PutMapping(value = "/my-topic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRegisterTopic(@RequestBody SignUpTopicDto body) {
        Boolean res = topicService
                .getUpdateMyTopicService()
                .execute(body);
        if (res) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(res, "Success!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(res, "Fail!"));
        }
    }

    /**
     * Find assign topic
     *
     * @return
     */
    @GetMapping(value = "/assign-topic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAssignTopic(@RequestParam(value = "search", defaultValue = "") String search) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        // hard code
        if (userPrincipal.getRoleCode().equals(ERole.USER)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ArrayList<>());
        }
        List<TopicRegisterDto> result = topicService
                .getFindAllTopicAssignService()
                .execute(new IFindAllTopicAssignService.Input(search, userPrincipal.getFacultyId(),
                        userPrincipal.getRoleCode()));
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    /**
     * Find My detail topic
     *
     * @return
     */
    @GetMapping(value = "/assign-topic/{topicId}/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDetailAssignTopic(@PathVariable("topicId") Long topicId, @PathVariable("teamId") Long teamId) {
        SignUpTopicFullDto result = topicService
                .getFindDetailTopicRegisterService()
                .execute(new IFindDetailTopicRegisterService.Input(topicId,teamId));
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    /**
     * acceptTopic
     *
     * @return
     */
    @PostMapping(value = "/assign-topic/approve/{topicId}/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> acceptTopic(@PathVariable("topicId") Long topicId, @PathVariable("teamId") Long teamId) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        Boolean res = false;
        // hard code
        if (!userPrincipal.getRoleCode().equals(ERole.USER)) {
            res = topicService
                    .getApproveTopicService()
                    .execute(new IApproveTopicService.Input(topicId, teamId, userPrincipal.getRoleCode()));
        }
        if (res) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(res, "Success!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(res, "Fail!"));
        }
    }

    /**
     * decline topic
     *
     * @return
     */
    @PostMapping(value = "/assign-topic/decline/{topicId}/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> declineTopic(@PathVariable("topicId") Long topicId, @PathVariable("teamId") Long teamId) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        Boolean res = false;
        // hard code
        if (!userPrincipal.getRoleCode().equals(ERole.USER)) {
            res = topicService
                    .getDeclineTopicService()
                    .execute(new IDeclineTopicService.Input(topicId, teamId, userPrincipal.getRoleCode()));
        }
        if (res) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(res, "Success!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(res, "Fail!"));
        }
    }

}
