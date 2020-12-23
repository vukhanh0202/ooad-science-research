package com.uit.ooad.scienceresearch.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uit.ooad.scienceresearch.constant.ERole;
import lombok.Data;

import java.util.Date;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountLecturerDto {

    private Long lecturerId;
    private String username;
    private String password;
    private ERole role;
    private String fullName;
    private Date dob;
    private String major;
    private String email;
    private String phone;
    private Long contractId;
    private Long facultyId;
    private Long accountId;
    private String position;
    private String degree;
}
