package com.uit.ooad.scienceresearch.service.topic.impl;

import com.uit.ooad.scienceresearch.constant.EProcess;
import com.uit.ooad.scienceresearch.constant.ERole;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.repository.SignUpTopicRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.topic.IApproveTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class ApproveTopicServiceImpl extends AbstractBaseService<IApproveTopicService.Input, Boolean>
        implements IApproveTopicService<IApproveTopicService.Input, Boolean> {

    @Autowired
    SignUpTopicRepository signUpTopicRepository;

    @Override
    public Boolean doing(Input input) {
        try {
            SignUpTopic signUpTopic = signUpTopicRepository
                    .findByTopicTopicIdAndTeamTeamId(input.getTopicId(),input.getTeamId());
            if (input.getRoleCode().equals(ERole.ADMIN)){
                if(signUpTopic.getUniversityReview().equals(EProcess.process)){
                    signUpTopic.setUniversityReview(EProcess.finish);
                    signUpTopic.setCompleted(EProcess.finish);
                    LocalDate now =  LocalDate.now();
                    signUpTopic.setDateApproved(now.toString());
                    signUpTopic.setDateExpired(now.plusYears(1).toString());
                }else{
                    return false;
                }
            }else if(input.getRoleCode().equals(ERole.MANAGER)){
                if(signUpTopic.getFacultyReview().equals(EProcess.process)){
                    signUpTopic.setFacultyReview(EProcess.finish);
                    if (signUpTopic.getUniversityReview().equals(EProcess.none)){
                        signUpTopic.setCompleted(EProcess.finish);
                        LocalDate now =  LocalDate.now();
                        signUpTopic.setDateApproved(now.toString());
                        signUpTopic.setDateExpired(now.plusYears(1).toString());
                    }else{
                        signUpTopic.setUniversityReview(EProcess.process);
                    }
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
