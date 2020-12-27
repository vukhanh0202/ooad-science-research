package com.uit.ooad.scienceresearch.dto.faculty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/27/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacultyInfoDto {
    private Long facultyId;
    private String nameFaculty;
    private String nameUniversity;
    private Long totalTopic;
    private Long totalLecturer;
}
