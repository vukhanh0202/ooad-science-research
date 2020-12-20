package com.uit.ooad.scienceresearch.service.topic.impl;

import com.uit.ooad.scienceresearch.dto.topic.SignUpTopicFullDto;
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
public class FindDetailTopicRegisterServiceImpl extends AbstractBaseService<IFindDetailTopicRegisterService.Input, SignUpTopicFullDto>
        implements IFindDetailTopicRegisterService<IFindDetailTopicRegisterService.Input, SignUpTopicFullDto> {

    @Autowired
    SignUpTopicRepository signUpTopicRepository;

    @Autowired
    SignUpTopicMapper signUpTopicMapper;


    @Override
    public SignUpTopicFullDto doing(Input input) {
        try {
            SignUpTopic signUpTopic = signUpTopicRepository
                    .findByTopicTopicIdAndTeamTeamId(input.getTopicId(),input.getTeamId());
            return signUpTopicMapper.toSignUpTopicFullDto(signUpTopic);
        } catch (Exception e) {
            return null;
        }
    }
}
