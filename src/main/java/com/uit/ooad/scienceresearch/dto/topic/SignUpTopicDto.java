package com.uit.ooad.scienceresearch.dto.topic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uit.ooad.scienceresearch.constant.EProcess;
import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/20/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignUpTopicDto {

    private Long teamId;
    private Long topicId;
    private EProcess start;
    private EProcess facultyReview;
    private EProcess universityReview;
    private EProcess completed;
    private String dateApproved;
    private String dateExpired;
    private String dateExtend;
    private Boolean result;
    private Boolean finish;
    private Long councilId;
}
