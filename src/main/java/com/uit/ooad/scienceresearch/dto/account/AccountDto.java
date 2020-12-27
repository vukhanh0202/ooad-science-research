package com.uit.ooad.scienceresearch.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uit.ooad.scienceresearch.constant.ERole;
import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {
    private static final long serialVersionUID = 5926468583005150707L;

    private Long accountId;
    private String username;
    private String password;
    private String fullName;
    private Long facultyId;
    private Long lecturerId;
    private ERole roleCode;
    private String facultyName;

    //need default constructor for JSON Parsing
    public AccountDto() {
    }

    public AccountDto(Long id,String password, String fullName) {
        this.accountId = id;
        this.username = username;
        this.fullName = fullName;
    }
}
