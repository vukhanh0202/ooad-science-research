package com.uit.ooad.scienceresearch.controller;

import com.uit.ooad.scienceresearch.dto.level.LevelDto;
import com.uit.ooad.scienceresearch.service.level.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/13/2020
 */
@RestController
@RequestMapping(value = "/request")
public class RequestController {

    @Autowired
    ILevelService levelService;

    /**
     * Find All Faculty No pageable
     *
     * @return
     */
    @GetMapping(value = "/my-request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMyRequest() {
        List<LevelDto> result = levelService.getFindAllNameLevelService().execute();
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }
}
