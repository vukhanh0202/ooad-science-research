package com.uit.ooad.scienceresearch.dto.topic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uit.ooad.scienceresearch.constant.EProcess;
import com.uit.ooad.scienceresearch.dto.team.TeamLecturerDto;
import lombok.Data;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/20/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InfoTopicDto {

    private TopicFullDto topic;
    private String dateApprove;
    private String result;
    private String finish;
    private String dateRegister;
}
