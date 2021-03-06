package com.uit.ooad.scienceresearch.dto.topic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uit.ooad.scienceresearch.constant.EProcess;
import com.uit.ooad.scienceresearch.dto.lecturer.LecturerDto;
import com.uit.ooad.scienceresearch.dto.lecturer.LecturerFullDto;
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
public class SignUpTopicFullDto {

    private List<TeamLecturerDto> team;
    private Long teamId;
    private TopicFullDto topic;
    private String leader;
    private EProcess start;
    private EProcess facultyReview;
    private EProcess universityReview;
    private EProcess completed;
    private Long current;
    private EProcess status;
    private String dateApproved;
    private String dateExpired;
    private String dateExtend;
    private String result;
    private String finish;
}
