package com.uit.ooad.scienceresearch.dto.council;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uit.ooad.scienceresearch.dto.team.TeamLecturerDto;
import lombok.Data;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/24/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopicReview {

    private Long topicId;
    private Long id;
    private Long teamId;
    private String leader;
    private String nameTopic;
    private String facultyName;
    private String levelName;
    private String fieldTopic;
    private String status;
    private Long year;
    private List<TeamLecturerDto> members;
}
