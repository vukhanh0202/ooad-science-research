package com.uit.ooad.scienceresearch.service.topic.impl;

import com.uit.ooad.scienceresearch.dto.topic.TopicRegisterDto;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.entity.Team;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.entity.join.TeamLecturer;
import com.uit.ooad.scienceresearch.mapper.topic.SignUpTopicMapper;
import com.uit.ooad.scienceresearch.repository.*;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.topic.IFindAllTopicRegisterService;
import com.uit.ooad.scienceresearch.service.topic.IFindDetailTopicRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class FindDetailTopicRegisterServiceImpl extends AbstractBaseService<Long, TopicRegisterDto>
        implements IFindDetailTopicRegisterService<Long, TopicRegisterDto> {


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
    public TopicRegisterDto doing(Long topicId) {
        try {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
