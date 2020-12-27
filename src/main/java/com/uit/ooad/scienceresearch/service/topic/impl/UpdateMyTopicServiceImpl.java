package com.uit.ooad.scienceresearch.service.topic.impl;

import com.uit.ooad.scienceresearch.constant.EProcess;
import com.uit.ooad.scienceresearch.constant.ERole;
import com.uit.ooad.scienceresearch.data.UserPrincipal;
import com.uit.ooad.scienceresearch.dto.topic.SignUpTopicDto;
import com.uit.ooad.scienceresearch.entity.Team;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.entity.join.TeamLecturer;
import com.uit.ooad.scienceresearch.exception.InvalidException;
import com.uit.ooad.scienceresearch.mapper.topic.SignUpTopicMapper;
import com.uit.ooad.scienceresearch.repository.SignUpTopicRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.topic.IApproveTopicService;
import com.uit.ooad.scienceresearch.service.topic.IUpdateMyTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Default.LEADER;

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
    public void preExecute(SignUpTopicDto signUpTopicDto) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Team team = signUpTopicRepository.findByTopicTopicIdAndTeamTeamId(signUpTopicDto.getTopicId(), signUpTopicDto.getTeamId()).getTeam();
        List<TeamLecturer> list = team.getGroupLecturers();
        for (TeamLecturer teamLecturer:list) {
            if (teamLecturer.getLecturer().getLecturerId().equals(userPrincipal.getLecturerId()) && !teamLecturer.isPrimary()){
                throw new InvalidException(messageHelper.getMessage(LEADER));
            }
        }

    }
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
