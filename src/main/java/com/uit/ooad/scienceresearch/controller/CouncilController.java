package com.uit.ooad.scienceresearch.controller;

import com.uit.ooad.scienceresearch.data.UserPrincipal;
import com.uit.ooad.scienceresearch.dto.council.*;
import com.uit.ooad.scienceresearch.payload.ApiResponse;
import com.uit.ooad.scienceresearch.service.council.*;
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
 * @since 12/24/2020
 */
@RestController
@RequestMapping(value = "/council")
public class CouncilController {

    @Autowired
    ICouncilService councilService;

    /**
     * Find Topic need to create a council review
     *
     * @return
     */
    @GetMapping(value = "/topic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDetailMyTopic() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        List<TopicReview> result = councilService
                .getFindAllTopicReviewService()
                .execute(new IFindAllTopicReviewService.Input(userPrincipal.getFacultyId(), userPrincipal.getRoleCode()));
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    /**
     * Create council
     *
     * @param body
     * @return
     */
    @PostMapping(value = "/create-council/{topicId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerTopic(@RequestBody List<CouncilLecturerDto> body,
                                           @PathVariable("topicId") Long topicId) {
        Boolean res = councilService.getCreateCouncilService().execute(new ICreateCouncilService.Input(topicId, body));
        if (res) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(res, "Success!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(res, "Fail!"));
        }
    }

    /**
     * Find List council
     *
     * @return
     */
    @GetMapping(value = "/faculty", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListCouncilFaculty() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        List<CouncilInfoDto> result = councilService
                .getFindAllListCouncilService()
                .execute(new IFindAllListCouncilService.Input(userPrincipal.getFacultyId()));
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    /**
     * Find List council
     *
     * @return
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListCouncilAll() {
        List<CouncilInfoDto> result = councilService
                .getFindAllListCouncilService()
                .execute(new IFindAllListCouncilService.Input(null));
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    /**
     * Find Dẻtail council
     *
     * @return
     */
    @GetMapping(value = "/{councilId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDetailCouncil(@PathVariable("councilId") Long councilId) {
        CouncilFullDto result = councilService
                .getFindDetailCouncilService()
                .execute(councilId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    /**
     * Find council review
     *
     * @return
     */
    @GetMapping(value = "/review", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReviewCouncil() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        List<ReviewCouncilByUserDto> result = councilService
                .getFindAllReviewCouncilByUserService()
                .execute(userPrincipal.getLecturerId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    /**
     * Find council review detail
     *
     * @return
     */
    @GetMapping(value = "/review-detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReviewCouncilDetail(@RequestParam(value = "councilId") Long councilId,
                                                    @RequestParam(value = "topicId") Long topicId,
                                                    @RequestParam(value = "teamId") Long teamId) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        DetailReviewCouncilByUserDto result = councilService
                .getFindDetailReviewCouncilByUserService()
                .execute(new IFindDetailReviewCouncilByUserService.Input(councilId, userPrincipal.getLecturerId(), teamId, topicId));
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    /**
     * Create council
     *
     * @param body
     * @return
     */
    @PostMapping(value = "/review-detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addReviewPost(@RequestBody RecordDto body) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        body.setLecturerId(userPrincipal.getLecturerId());
        Boolean res = councilService.getAddReviewCouncilService().execute(body);
        if (res) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(res, "Success!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(res, "Fail!"));
        }
    }

    /**
     * Find List council
     *
     * @return
     */
    @GetMapping(value = "/my-council", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListMyCouncil() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        List<CouncilInfoDto> result = councilService
                .getFindMyCouncilService()
                .execute(userPrincipal.getLecturerId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }
}
