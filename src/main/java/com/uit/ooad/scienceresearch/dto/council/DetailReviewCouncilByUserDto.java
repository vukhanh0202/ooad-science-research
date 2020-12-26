package com.uit.ooad.scienceresearch.dto.council;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uit.ooad.scienceresearch.dto.topic.TopicFullDto;
import lombok.Data;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/26/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailReviewCouncilByUserDto {

    private TopicFullDto topic;
    private List<RecordDto> members;
    private Long score;
    private String comment;
}
