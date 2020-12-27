package com.uit.ooad.scienceresearch.controller;

import com.uit.ooad.scienceresearch.constant.DefaultConstant;
import com.uit.ooad.scienceresearch.dto.account.AccountLecturerSearchDto;
import com.uit.ooad.scienceresearch.dto.lecturer.LecturerDto;
import com.uit.ooad.scienceresearch.dto.lecturer.LecturerFullDto;
import com.uit.ooad.scienceresearch.payload.ApiResponse;
import com.uit.ooad.scienceresearch.payload.PaginationResponse;
import com.uit.ooad.scienceresearch.service.lecturer.ICountLecturerService;
import com.uit.ooad.scienceresearch.service.lecturer.IFindAllLecturerRegisterTopicService;
import com.uit.ooad.scienceresearch.service.lecturer.IFindAllLecturerService;
import com.uit.ooad.scienceresearch.service.lecturer.ILecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/8/2020
 */
@RestController
@RequestMapping(value = "/lecturer")
public class LecturerController {


    @Autowired
    ILecturerService lecturerService;

    /**
     * Find All Lecturer
     *
     * @return
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllLecturer(@RequestParam(value = "page", defaultValue = DefaultConstant.PAGE_NUMBER_DEFAULT) Integer page,
                                            @RequestParam(value = "size", defaultValue = DefaultConstant.PAGE_SIZE_DEFAULT) Integer size,
                                            @RequestParam(value = "search", defaultValue = "") String search,
                                            @RequestParam(value = "facultyId", defaultValue = "") Long facultyId,
                                            @RequestParam(value = "contractId", defaultValue = "") Long contractId) {
        List<LecturerFullDto> result = lecturerService.getFindAllLecturerService()
                .execute(new IFindAllLecturerService.Input(search, facultyId, contractId, page, size));

        Long totalItem = lecturerService.getCountLecturerService().execute(new ICountLecturerService.Input(search, facultyId, contractId, page, size));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PaginationResponse(Integer.parseInt(totalItem.toString())
                        , size, page, result));
    }

    /**
     * Find Lecturer for register topic
     *
     * @return
     */
    @GetMapping(value = "/find-register/{topicId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLecturerForRegisterTopic(@RequestParam(value = "search", defaultValue = "") String search,
                                                         @PathVariable("topicId") Long topicId) {
        List<AccountLecturerSearchDto> rs = lecturerService
                .getFindAllLecturerRegisterTopicService()
                .execute(new IFindAllLecturerRegisterTopicService.Input(search, topicId));
        return ResponseEntity.status(HttpStatus.OK)
                .body(rs);
    }

    /**
     * Find Lecturer with Id
     *
     * @return
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLecturerById(@PathVariable("id") Long lecturerId) {
        LecturerFullDto result = lecturerService.getFindLecturerByIdService().execute(lecturerId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(result));
    }

    /**
     * Update topic
     *
     * @param body
     * @return
     */
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateLecturer(@RequestBody LecturerDto body) {
        Boolean res = lecturerService.getUpdateLecturerService().execute(body);
        if (res) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(res, "Success!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(res, "Fail!"));
        }
    }

    /**
     * Delete lecturer
     *
     * @param body
     * @return
     */
   /* @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteTopic(@RequestBody LecturerDto body) {
        Boolean res = lecturerService.getDeleteLecturerService().execute(body);
        if (res) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(res, "Success!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(res, "Fail!"));
        }
    }*/
}
