package com.uit.ooad.scienceresearch.service.faculty;

import com.uit.ooad.scienceresearch.dto.faculty.FacultyDto;
import com.uit.ooad.scienceresearch.dto.faculty.FacultyFullDto;
import com.uit.ooad.scienceresearch.dto.faculty.FacultyInfoDto;
import com.uit.ooad.scienceresearch.dto.faculty.TopicFacultyDto;
import com.uit.ooad.scienceresearch.dto.lecturer.LecturerTopicRegisterDto;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
public interface IFacultyService {

    IFindAllFacultyService<IFindAllFacultyService.Input, List<FacultyInfoDto>> getFindAllFacultyService();

    IFindAllNameFacultyService<Void, List<FacultyDto>> getFindAllNameFacultyService();

    ICountFacultyService<Void, Long> getCountFacultyService();

    IFindFacultyByIdService<Long, FacultyFullDto> getFindFacultyByIdService();

    IAddFacultyService<FacultyFullDto, Boolean> getAddFacultyService();

    IUpdateFacultyService<FacultyFullDto, Boolean> getUpdateFacultyService();

    IFindDetailTopicOfFacultyService<IFindDetailTopicOfFacultyService.Input, TopicFacultyDto> getFindDetailTopicOfFacultyService();

    IFindDetailLecturerOfFacultyService<Long, LecturerTopicRegisterDto> getFindDetailLecturerOfFacultyService();
}
