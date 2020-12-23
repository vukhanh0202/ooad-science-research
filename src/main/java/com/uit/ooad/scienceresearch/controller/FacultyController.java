package com.uit.ooad.scienceresearch.controller;

import com.uit.ooad.scienceresearch.constant.DefaultConstant;
import com.uit.ooad.scienceresearch.dto.faculty.FacultyDto;
import com.uit.ooad.scienceresearch.dto.faculty.FacultyFullDto;
import com.uit.ooad.scienceresearch.dto.faculty.TopicFacultyDto;
import com.uit.ooad.scienceresearch.dto.lecturer.LecturerFullDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicFullDto;
import com.uit.ooad.scienceresearch.payload.ApiResponse;
import com.uit.ooad.scienceresearch.payload.PaginationResponse;
import com.uit.ooad.scienceresearch.service.faculty.IFacultyService;
import com.uit.ooad.scienceresearch.service.faculty.IFindAllFacultyService;
import com.uit.ooad.scienceresearch.service.faculty.IFindDetailTopicOfFacultyService;
import com.uit.ooad.scienceresearch.service.lecturer.ICountLecturerService;
import com.uit.ooad.scienceresearch.service.lecturer.IFindAllLecturerService;
import com.uit.ooad.scienceresearch.service.lecturer.ILecturerService;
import com.uit.ooad.scienceresearch.service.topic.ICountTopicService;
import com.uit.ooad.scienceresearch.service.topic.IFindAllTopicService;
import com.uit.ooad.scienceresearch.service.topic.IFindDetailTopicRegisterService;
import com.uit.ooad.scienceresearch.service.topic.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/10/2020
 */
@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {


    @Autowired
    IFacultyService facultyService;

    @Autowired
    ITopicService topicService;

    @Autowired
    ILecturerService lecturerService;

    /**
     * Find All Faculty
     *
     * @return
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllFaculty(@RequestParam(value = "page", defaultValue = DefaultConstant.PAGE_NUMBER_DEFAULT) Integer page,
                                           @RequestParam(value = "size", defaultValue = DefaultConstant.PAGE_SIZE_DEFAULT) Integer size,
                                           @RequestParam(value = "search", defaultValue = "") String search) {
        List<FacultyFullDto> result = facultyService.getFindAllFacultyService().execute(new IFindAllFacultyService.Input(search, page, size));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PaginationResponse(Integer.parseInt(facultyService.getCountFacultyService().execute().toString())
                        , size, page, result));
    }

    /**
     * Find All Faculty No pageable
     *
     * @return
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllFacultyNoPageable() {
        List<FacultyDto> result = facultyService.getFindAllNameFacultyService().execute();
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }


    /**
     * Find Faculty with Id
     *
     * @return
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFacultyById(@PathVariable("id") Long facultyId) {
        FacultyFullDto result = facultyService.getFindFacultyByIdService().execute(facultyId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(result));
    }

    /**
     * Add new Faculty
     *
     * @param body
     * @return
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addFaculty(@RequestBody FacultyFullDto body) {
        Boolean res = facultyService.getAddFacultyService().execute(body);
        if (res) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(res, "Success!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(res, "Fail!"));
        }
    }

    /**
     * Update Faculty
     *
     * @param body
     * @return
     */
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateFaculty(@RequestBody FacultyFullDto body) {
        Boolean res = facultyService.getUpdateFacultyService().execute(body);
        if (res) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(res, "Success!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(res, "Fail!"));
        }
    }


    /**
     * Find All Topic Of Faculty
     *
     * @return
     */
    @GetMapping(value = "/topic/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTopic(@RequestParam(value = "page", defaultValue = DefaultConstant.PAGE_NUMBER_DEFAULT) Integer page,
                                         @RequestParam(value = "size", defaultValue = DefaultConstant.PAGE_SIZE_DEFAULT) Integer size,
                                         @RequestParam(value = "search", defaultValue = "") String search,
                                         @RequestParam(value = "levelId", defaultValue = "") Long levelId,
                                         @RequestParam(value = "fieldId", defaultValue = "") Long fieldId,
                                         @RequestParam(value = "year", defaultValue = "") Long year,
                                         @RequestParam(value = "deleted", defaultValue = "") Boolean deleted,
                                         @PathVariable("id") Long facultyId) {
        List<TopicFullDto> result = topicService
                .getFindAllTopicService()
                .execute(new IFindAllTopicService.Input(search, facultyId, levelId, fieldId, year, deleted, page, size));

        Long totalItem = topicService.getCountTopicService().execute(new ICountTopicService.Input(search, facultyId, levelId, fieldId, year, deleted));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PaginationResponse(Integer.parseInt(totalItem.toString())
                        , size, page, result));
    }

    /**
     * File ALl Lecturer of Faculty
     *
     * @param page
     * @param size
     * @param search
     * @param facultyId
     * @param contractId
     * @return
     */
    @GetMapping(value = "/lecturer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllLecturer(@RequestParam(value = "page", defaultValue = DefaultConstant.PAGE_NUMBER_DEFAULT) Integer page,
                                            @RequestParam(value = "size", defaultValue = DefaultConstant.PAGE_SIZE_DEFAULT) Integer size,
                                            @RequestParam(value = "search", defaultValue = "") String search,
                                            @RequestParam(value = "contractId", defaultValue = "") Long contractId,
                                            @PathVariable("id") Long facultyId) {
        List<LecturerFullDto> result = lecturerService.getFindAllLecturerService()
                .execute(new IFindAllLecturerService.Input(search, facultyId, contractId, page, size));

        Long totalItem = lecturerService.getCountLecturerService().execute(new ICountLecturerService.Input(search, facultyId, contractId, page, size));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PaginationResponse(Integer.parseInt(totalItem.toString())
                        , size, page, result));
    }

    /**
     * Find Detail Topic Of Faculty
     *
     * @return
     */
    @GetMapping(value = "/topic/detail/{topicId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findDetailTopicOfFaculty(@PathVariable("topicId") Long topicId) {
        TopicFacultyDto result = facultyService
                .getFindDetailTopicOfFacultyService()
                .execute(new IFindDetailTopicOfFacultyService.Input(topicId));

        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }
}
