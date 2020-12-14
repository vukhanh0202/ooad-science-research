package com.uit.ooad.scienceresearch.controller;

import com.uit.ooad.scienceresearch.constant.DefaultConstant;
import com.uit.ooad.scienceresearch.dto.faculty.FacultyDto;
import com.uit.ooad.scienceresearch.dto.faculty.FacultyFullDto;
import com.uit.ooad.scienceresearch.payload.ApiResponse;
import com.uit.ooad.scienceresearch.payload.PaginationResponse;
import com.uit.ooad.scienceresearch.service.faculty.IFacultyService;
import com.uit.ooad.scienceresearch.service.faculty.IFindAllFacultyService;
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
     * Update contract
     *
     * @param body
     * @return
     */
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateContract(@RequestBody FacultyFullDto body) {
        Boolean res = facultyService.getUpdateFacultyService().execute(body);
        if (res) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(res, "Success!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(res, "Fail!"));
        }
    }

}
