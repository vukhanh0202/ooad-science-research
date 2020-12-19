package com.uit.ooad.scienceresearch.service.topic.impl;

import com.uit.ooad.scienceresearch.dto.topic.TopicFullDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicRegisterDto;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.entity.Team;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.entity.join.TeamLecturer;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.topic.SignUpTopicMapper;
import com.uit.ooad.scienceresearch.mapper.topic.TopicMapper;
import com.uit.ooad.scienceresearch.repository.*;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.topic.IFindAllTopicRegisterService;
import com.uit.ooad.scienceresearch.service.topic.IFindAllTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Faculty.FACULTY_NOT_FOUND;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Field.FIELD_NOT_FOUND;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Level.LEVEL_NOT_FOUND;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class FindAllTopicRegisterServiceImpl extends AbstractBaseService<IFindAllTopicRegisterService.Input, List<TopicRegisterDto>>
        implements IFindAllTopicRegisterService<IFindAllTopicRegisterService.Input, List<TopicRegisterDto>> {


    @Autowired
    LecturerRepository lecturerRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    SignUpTopicMapper signUpTopicMapper;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    LevelRepository levelRepository;

    @Autowired
    FieldTopicRepository fieldTopicRepository;


    @Override
    public List<TopicRegisterDto> doing(Input input) {
        try {
            Lecturer lecturer = lecturerRepository.findById(input.getLecturerId()).get();
            List<Team> teams = lecturer.getGroupLecturers()
                    .stream()
                    .map(TeamLecturer::getTeam)
                    .collect(Collectors.toList());
            List<SignUpTopic> signUps = teams
                    .stream()
                    .map(team -> team.getSignUpTopics().get(0))
                    .collect(Collectors.toList());
            List<TopicRegisterDto> result =  signUpTopicMapper.toListTopicRegisterDto(signUps);
            result.removeIf(topicRegisterDto -> !topicRegisterDto.getNameTopic().contains(input.getSearch()));
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
