package com.uit.ooad.scienceresearch.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uit.ooad.scienceresearch.dto.account.AccountDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/9/2020
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Boolean isDeleted;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date createdAt;
    private AccountDto createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date updatedAt;
    private AccountDto updatedBy;

}