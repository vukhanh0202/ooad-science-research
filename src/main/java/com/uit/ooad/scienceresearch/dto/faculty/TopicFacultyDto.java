package com.uit.ooad.scienceresearch.dto.faculty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uit.ooad.scienceresearch.dto.topic.SignUpTopicForFaculty;
import com.uit.ooad.scienceresearch.dto.topic.SignUpTopicFullDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicFullDto;
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
public class TopicFacultyDto {

    private TopicFullDto topic;
    private List<SignUpTopicFullDto> teamList;
}
