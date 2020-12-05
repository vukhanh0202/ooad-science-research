package com.uit.ooad.scienceresearch.controller;

import com.uit.ooad.scienceresearch.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTaskDetail() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse("Hello world"));
    }
}
