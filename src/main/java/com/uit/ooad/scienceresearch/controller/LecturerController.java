package com.uit.ooad.scienceresearch.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/8/2020
 */
@RestController
@RequestMapping(value = "/lecturer")
public class LecturerController {


    /**
     * Find All Lecturer
     *
     * @return
     */
   /* @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllLecturer() {
        List<Lecturer> result = topicService.getFindAllTopicService().execute();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(result));
    }*/
}
