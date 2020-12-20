package com.uit.ooad.scienceresearch.service.topic.impl;

import com.uit.ooad.scienceresearch.constant.EProcess;
import com.uit.ooad.scienceresearch.constant.ERole;
import com.uit.ooad.scienceresearch.dto.topic.TopicRegisterDto;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.mapper.topic.SignUpTopicMapper;
import com.uit.ooad.scienceresearch.repository.*;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.topic.IFindAllTopicAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class FindAllTopicAssignServiceImpl extends AbstractBaseService<IFindAllTopicAssignService.Input, List<TopicRegisterDto>>
        implements IFindAllTopicAssignService<IFindAllTopicAssignService.Input, List<TopicRegisterDto>> {


    @Autowired
    SignUpTopicMapper signUpTopicMapper;

    @Autowired
    SignUpTopicRepository signUpTopicRepository;

    @Autowired
    TopicRepository topicRepository;

    @Override
    public List<TopicRegisterDto> doing(Input input) {
        try {
            List<Long> topicIds = topicRepository
                    .findAllByFacultyFacultyId(input.getFacultyId())
                    .stream()
                    .map(Topic::getTopicId)
                    .collect(Collectors.toList());
            Set<Long> topicIdSets = new HashSet<>(topicIds);
            List<SignUpTopic> signUps = new ArrayList<>();
            if (input.getRoleCode().equals(ERole.ADMIN)){
                signUps = signUpTopicRepository.findCustomByUniversityReview(EProcess.process.ordinal());
            }else if (input.getRoleCode().equals(ERole.MANAGER)){
                signUps = signUpTopicRepository.findCustomByFacultyReviewAndListTopicId(topicIdSets, EProcess.process.ordinal());
            }
            List<TopicRegisterDto> result =  signUpTopicMapper.toListTopicRegisterDto(signUps);
            result.removeIf(topicRegisterDto -> !topicRegisterDto.getNameTopic().contains(input.getSearch()));
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
