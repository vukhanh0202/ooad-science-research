package com.uit.ooad.scienceresearch.dto.council;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/25/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecordDto {

    private Long councilId;
    private Long topicId;
    private Long teamId;
    private Long lecturerId;
    private Long score;
}
