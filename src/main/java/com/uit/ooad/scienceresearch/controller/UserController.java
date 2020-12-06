package com.uit.ooad.scienceresearch.controller;

import com.uit.ooad.scienceresearch.dto.account.AccountDto;
import com.uit.ooad.scienceresearch.dto.ApiResponse;
import com.uit.ooad.scienceresearch.dto.account.AccountLecturerDto;
import com.uit.ooad.scienceresearch.entity.Account;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.service.account.IAccountService;
import com.uit.ooad.scienceresearch.service.lecturer.ILecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTaskDetail() {
        String x = BCrypt.hashpw("password", BCrypt.gensalt(10));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(x));
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody AccountLecturerDto body) {
        Account account = accountService.getRegisterAccountService().execute(body);
        body.setId(account.getId());
        Lecturer lecturer = lecturerService.getRegisterLecturerService().execute(body);
        if (account != null && lecturer != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Success!"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Fail!"));
    }
}
