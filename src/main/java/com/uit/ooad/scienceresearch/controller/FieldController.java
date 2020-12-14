package com.uit.ooad.scienceresearch.controller;

import com.uit.ooad.scienceresearch.dto.field.FieldTopicDto;
import com.uit.ooad.scienceresearch.entity.FieldTopic;
import com.uit.ooad.scienceresearch.service.field.IFieldService;
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
@RequestMapping(value = "/field")
public class FieldController {

    @Autowired
    IFieldService fieldService;

    /**
     * Find All Faculty No pageable
     *
     * @return
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllFacultyNoPageable() {
        List<FieldTopicDto> result = fieldService.getFindAllNameFieldService().execute();
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }
}
