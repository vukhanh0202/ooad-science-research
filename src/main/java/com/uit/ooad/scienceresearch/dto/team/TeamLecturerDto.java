package com.uit.ooad.scienceresearch.dto.team;

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
public class TeamLecturerDto {

    private Long lecturerId;
    private String username;
    private String fullName;
    private Long teamId;
    private String position;
    private boolean isPrimary;
}
