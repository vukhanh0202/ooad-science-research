package com.uit.ooad.scienceresearch.dto.topic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uit.ooad.scienceresearch.constant.EProcess;
import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/23/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignUpTopicForFaculty {

    private Long teamId;
    private String leader;
    private EProcess status;
    private String dateApproved;
    private String dateExpired;
    private String dateExtend;
    private String result;
    private String finish;
}
