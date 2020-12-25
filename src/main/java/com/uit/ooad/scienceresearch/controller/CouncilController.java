package com.uit.ooad.scienceresearch.controller;

import com.uit.ooad.scienceresearch.data.UserPrincipal;
import com.uit.ooad.scienceresearch.dto.council.CouncilLecturerDto;
import com.uit.ooad.scienceresearch.dto.council.TopicReview;
import com.uit.ooad.scienceresearch.payload.ApiResponse;
import com.uit.ooad.scienceresearch.service.council.ICouncilService;
import com.uit.ooad.scienceresearch.service.council.ICreateCouncilService;
import com.uit.ooad.scienceresearch.service.council.IFindAllTopicReviewService;
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
}
