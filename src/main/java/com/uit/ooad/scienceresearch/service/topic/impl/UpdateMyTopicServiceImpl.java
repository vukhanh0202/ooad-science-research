package com.uit.ooad.scienceresearch.service.topic.impl;

import com.uit.ooad.scienceresearch.constant.EProcess;
import com.uit.ooad.scienceresearch.constant.ERole;
import com.uit.ooad.scienceresearch.dto.topic.SignUpTopicDto;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.mapper.topic.SignUpTopicMapper;
import com.uit.ooad.scienceresearch.repository.SignUpTopicRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.topic.IApproveTopicService;
import com.uit.ooad.scienceresearch.service.topic.IUpdateMyTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class UpdateMyTopicServiceImpl extends AbstractBaseService<SignUpTopicDto, Boolean>
        implements IUpdateMyTopicService<SignUpTopicDto, Boolean> {

    @Autowired
    SignUpTopicRepository signUpTopicRepository;

    @Autowired
    SignUpTopicMapper signUpTopicMapper;

    @Override
    public Boolean doing(SignUpTopicDto signUpTopicDto) {
        try {
            SignUpTopic oldTopic = signUpTopicRepository
                    .findByTopicTopicIdAndTeamTeamId(signUpTopicDto.getTopicId(),signUpTopicDto.getTeamId());
            signUpTopicMapper.updateMyTopic(signUpTopicDto, oldTopic);
            signUpTopicRepository.save(oldTopic);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
