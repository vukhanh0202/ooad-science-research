package com.uit.ooad.scienceresearch.dto.topic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopicDto {

    private Long id;
    private String nameTopic;
    private Long year;
    private Long faculty_id;
    private Long level_id;
    private Long field_id;
}
