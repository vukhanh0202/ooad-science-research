package com.uit.ooad.scienceresearch.dto.topic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uit.ooad.scienceresearch.dto.faculty.FacultyDto;
import com.uit.ooad.scienceresearch.dto.field.FieldTopicDto;
import com.uit.ooad.scienceresearch.dto.level.LevelDto;
import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/20/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopicRegisterDto {

    private Long topicId;
    private Long teamId;
    private String nameTopic;
    private String description;
    private String facultyName;
    private String levelName;
    private String fieldTopic;
    private String status;
    private Long year;
}
