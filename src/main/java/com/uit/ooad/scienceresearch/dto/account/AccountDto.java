package com.uit.ooad.scienceresearch.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    private String username;
    private String password;

    //need default constructor for JSON Parsing
    public AccountDto(){}
}
