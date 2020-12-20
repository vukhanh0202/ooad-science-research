package com.uit.ooad.scienceresearch.service.topic.impl;

import com.uit.ooad.scienceresearch.constant.EProcess;
import com.uit.ooad.scienceresearch.constant.ERole;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.repository.SignUpTopicRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.topic.IApproveTopicService;
import com.uit.ooad.scienceresearch.service.topic.IDeclineTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class DeclineTopicServiceImpl extends AbstractBaseService<IDeclineTopicService.Input, Boolean>
        implements IDeclineTopicService<IDeclineTopicService.Input, Boolean> {

    @Autowired
    SignUpTopicRepository signUpTopicRepository;

    @Override
    public Boolean doing(Input input) {
        try {
            SignUpTopic signUpTopic = signUpTopicRepository
                    .findByTopicTopicIdAndTeamTeamId(input.getTopicId(),input.getTeamId());
            if (input.getRoleCode().equals(ERole.ADMIN)){
                if(signUpTopic.getUniversityReview().equals(EProcess.process)){
                    signUpTopic.setUniversityReview(EProcess.error);
                }else{
                    return false;
                }
            }else if(input.getRoleCode().equals(ERole.MANAGER)){
                if(signUpTopic.getFacultyReview().equals(EProcess.process)){
                    signUpTopic.setFacultyReview(EProcess.error);
                }else{
                    return false;
                }
            }
            signUpTopicRepository.save(signUpTopic);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
