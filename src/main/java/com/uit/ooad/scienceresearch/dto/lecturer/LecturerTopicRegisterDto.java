package com.uit.ooad.scienceresearch.dto.lecturer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uit.ooad.scienceresearch.dto.BaseDto;
import com.uit.ooad.scienceresearch.dto.topic.InfoTopicDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/9/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LecturerTopicRegisterDto{

    private LecturerFullDto lecturer;
    private List<InfoTopicDto> listTopicRegister;
}
