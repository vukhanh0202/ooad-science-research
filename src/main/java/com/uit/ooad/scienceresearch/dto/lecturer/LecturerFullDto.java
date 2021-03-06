package com.uit.ooad.scienceresearch.dto.lecturer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uit.ooad.scienceresearch.constant.ERole;
import com.uit.ooad.scienceresearch.dto.BaseDto;
import lombok.Data;

import java.util.Date;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/9/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LecturerFullDto extends BaseDto{

    private Long lecturerId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dob;
    private String fullName;
    private String username;
    private ERole role;
    private String major;
    private String email;
    private String phone;
    private String degree;
    private String contract;
    private String faculty;
    private String position;
}
