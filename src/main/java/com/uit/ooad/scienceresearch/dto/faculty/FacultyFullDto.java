package com.uit.ooad.scienceresearch.dto.faculty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uit.ooad.scienceresearch.dto.BaseDto;
import com.uit.ooad.scienceresearch.dto.lecturer.LecturerFullDto;
import com.uit.ooad.scienceresearch.mapper.BaseMapper;
import lombok.Data;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/8/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacultyFullDto extends BaseDto {

    private Long facultyId;
    private String nameFaculty;
    private String nameUniversity;
    private Long totalTopic;
    private Long totalLecturer;
    List<LecturerFullDto> lecturers;
}
