package com.uit.ooad.scienceresearch.service.faculty.impl;

import com.uit.ooad.scienceresearch.dto.faculty.TopicFacultyDto;
import com.uit.ooad.scienceresearch.dto.lecturer.LecturerTopicRegisterDto;
import com.uit.ooad.scienceresearch.entity.join.TeamLecturer;
import com.uit.ooad.scienceresearch.mapper.lecturer.LecturerMapper;
import com.uit.ooad.scienceresearch.mapper.topic.SignUpTopicMapper;
import com.uit.ooad.scienceresearch.mapper.topic.TopicMapper;
import com.uit.ooad.scienceresearch.repository.*;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.faculty.IFindDetailLecturerOfFacultyService;
import com.uit.ooad.scienceresearch.service.faculty.IFindDetailTopicOfFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class FindDetailLecturerOfFacultyServiceImpl extends AbstractBaseService<Long, LecturerTopicRegisterDto>
        implements IFindDetailLecturerOfFacultyService<Long, LecturerTopicRegisterDto> {

    @Autowired
    LecturerRepository lecturerRepository;

    @Autowired
    LecturerMapper lecturerMapper;

    @Autowired
    SignUpTopicMapper signUpTopicMapper;

    @Autowired
    SignUpTopicRepository signUpTopicRepository;

    @Autowired
    TeamLecturerRepository teamLecturerRepository;

    @Override
    public LecturerTopicRegisterDto doing(Long lecturerId) {
        try {
            List<TeamLecturer> teamList = teamLecturerRepository.findAllByLecturerLecturerId(lecturerId);
            List<Long> teamIdList = teamList.stream().map(teamLecturer -> teamLecturer.getTeam().getTeamId()).collect(Collectors.toList());
            Set<Long> teamIdSet = new HashSet<>(teamIdList);
            LecturerTopicRegisterDto rs = new LecturerTopicRegisterDto();
            rs.setLecturer(lecturerMapper.toLecturerFullDto(lecturerRepository.findById(lecturerId).get()));
            rs.setListTopicRegister(signUpTopicMapper.toListInfoTopicDto(signUpTopicRepository.findCustomByTeamId(teamIdSet)));
            return rs;
        } catch (Exception e) {
            return null;
        }
    }
}
