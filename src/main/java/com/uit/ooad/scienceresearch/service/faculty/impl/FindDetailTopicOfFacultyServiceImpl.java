package com.uit.ooad.scienceresearch.service.faculty.impl;

import com.uit.ooad.scienceresearch.dto.faculty.FacultyFullDto;
import com.uit.ooad.scienceresearch.dto.faculty.TopicFacultyDto;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.mapper.faculty.FacultyMapper;
import com.uit.ooad.scienceresearch.mapper.topic.SignUpTopicMapper;
import com.uit.ooad.scienceresearch.mapper.topic.TopicMapper;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.repository.SignUpTopicRepository;
import com.uit.ooad.scienceresearch.repository.TopicRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.faculty.IFindAllFacultyService;
import com.uit.ooad.scienceresearch.service.faculty.IFindDetailTopicOfFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
public class FindDetailTopicOfFacultyServiceImpl extends AbstractBaseService<IFindDetailTopicOfFacultyService.Input, TopicFacultyDto>
        implements IFindDetailTopicOfFacultyService<IFindDetailTopicOfFacultyService.Input, TopicFacultyDto> {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TopicMapper topicMapper;

    @Autowired
    SignUpTopicMapper signUpTopicMapper;

    @Autowired
    SignUpTopicRepository signUpTopicRepository;


    @Override
    public TopicFacultyDto doing(Input input) {
        try {
            TopicFacultyDto rs = new TopicFacultyDto();
            rs.setTopic(topicMapper.toTopicFullDto(topicRepository.findById(input.getTopicId()).get()));
            rs.setTeamList(signUpTopicMapper.toListSignUpTopicFullDto(signUpTopicRepository.findAllByTopicTopicId(input.getTopicId())));
            return rs;
        } catch (Exception e) {
            return null;
        }
    }
}
