package com.uit.ooad.scienceresearch.controller;

import com.uit.ooad.scienceresearch.constant.DefaultConstant;
import com.uit.ooad.scienceresearch.data.UserPrincipal;
import com.uit.ooad.scienceresearch.dto.account.AccountDto;
import com.uit.ooad.scienceresearch.dto.account.AccountLecturerSearchDto;
import com.uit.ooad.scienceresearch.dto.faculty.FacultyFullDto;
import com.uit.ooad.scienceresearch.dto.lecturer.LecturerFullDto;
import com.uit.ooad.scienceresearch.payload.ApiResponse;
import com.uit.ooad.scienceresearch.dto.account.AccountLecturerDto;
import com.uit.ooad.scienceresearch.entity.Account;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.payload.PaginationResponse;
import com.uit.ooad.scienceresearch.service.account.IAccountService;
import com.uit.ooad.scienceresearch.service.faculty.IFacultyService;
import com.uit.ooad.scienceresearch.service.faculty.IFindAllFacultyService;
import com.uit.ooad.scienceresearch.service.lecturer.ICountLecturerService;
import com.uit.ooad.scienceresearch.service.lecturer.IFindAllLecturerRegisterTopicService;
import com.uit.ooad.scienceresearch.service.lecturer.IFindAllLecturerService;
import com.uit.ooad.scienceresearch.service.lecturer.ILecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    IAccountService accountService;

    @Autowired
    ILecturerService lecturerService;

    @Autowired
    IFacultyService facultyService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody AccountLecturerDto body) {
        Account account = accountService.getRegisterAccountService().execute(body);
        body.setAccountId(account.getAccountId());
        Lecturer lecturer = lecturerService.getRegisterLecturerService().execute(body);
        if (account.getUsername() != null && lecturer.getLecturerId() != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Success!"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Fail!"));
    }

    @GetMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> info() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccountDto account = new AccountDto();
        account.setUsername(userPrincipal.getUsername());
        account.setFullName(userPrincipal.getFullName());
        account.setFacultyId(userPrincipal.getFacultyId());
        account.setLecturerId(userPrincipal.getLecturerId());
        account.setRoleCode(userPrincipal.getRoleCode());
        account.setFacultyName(userPrincipal.getNameFaculty());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(account));
    }

    @GetMapping(value = "/faculty", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> facultyOfUser(@RequestParam(value = "page", defaultValue = DefaultConstant.PAGE_NUMBER_DEFAULT) Integer page,
                                           @RequestParam(value = "size", defaultValue = DefaultConstant.PAGE_SIZE_DEFAULT) Integer size,
                                           @RequestParam(value = "search", defaultValue = "") String search,
                                           @RequestParam(value = "contractId", defaultValue = "") Long contractId) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FacultyFullDto result1 = facultyService.getFindFacultyByIdService().execute(userPrincipal.getFacultyId());
        List<LecturerFullDto> result = lecturerService.getFindAllLecturerService()
                .execute(new IFindAllLecturerService.Input(search, userPrincipal.getFacultyId(), contractId, page, size));

        Long totalItem = lecturerService.getCountLecturerService().execute(new ICountLecturerService.Input(search, userPrincipal.getFacultyId(), contractId, page, size));

        Map<String, Object> rs = new HashMap<>();
        rs.put("info", result1);
        rs.put("lecturers", new PaginationResponse(Integer.parseInt(totalItem.toString())
                , size, page, result));
        return ResponseEntity.status(HttpStatus.OK)
                .body(rs);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchUser(@RequestParam(value = "search", defaultValue = "") String search) {
        List<AccountLecturerSearchDto> rs = accountService.getSearchAccountService().execute(search);
        return ResponseEntity.status(HttpStatus.OK)
                .body(rs);
    }

    @GetMapping(value = "/check-register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkRegister() {
        Boolean rs = false;
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Long> list = lecturerService
                .getFindAllLecturerRegisterTopicService()
                .execute(new IFindAllLecturerRegisterTopicService.Input("", null))
                .stream().map(AccountLecturerSearchDto::getLecturerId).collect(Collectors.toList());
        if (!list.contains(userPrincipal.getLecturerId())) {
            rs = true;
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(rs);
    }
}
