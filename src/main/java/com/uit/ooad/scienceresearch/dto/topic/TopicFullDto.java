package com.uit.ooad.scienceresearch.dto.topic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uit.ooad.scienceresearch.dto.BaseDto;
import com.uit.ooad.scienceresearch.dto.faculty.FacultyDto;
import com.uit.ooad.scienceresearch.dto.faculty.FacultyFullDto;
import com.uit.ooad.scienceresearch.dto.field.FieldTopicDto;
import com.uit.ooad.scienceresearch.dto.level.LevelDto;
import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/8/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopicFullDto extends BaseDto {

    private Long topicId;
    private String nameTopic;
    private Long year;
    private FacultyDto faculty;
    private LevelDto level;
    private FieldTopicDto fieldTopic;

}
