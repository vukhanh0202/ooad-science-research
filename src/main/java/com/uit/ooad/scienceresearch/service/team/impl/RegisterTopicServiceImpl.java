package com.uit.ooad.scienceresearch.service.team.impl;

import com.uit.ooad.scienceresearch.constant.EProcess;
import com.uit.ooad.scienceresearch.data.UserPrincipal;
import com.uit.ooad.scienceresearch.dto.team.TeamLecturerDto;
import com.uit.ooad.scienceresearch.dto.topic.SignUpTopicDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicDto;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.entity.Team;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.exception.BadRequestException;
import com.uit.ooad.scienceresearch.exception.InvalidException;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.team.TeamLecturerMapper;
import com.uit.ooad.scienceresearch.mapper.topic.SignUpTopicMapper;
import com.uit.ooad.scienceresearch.mapper.topic.TopicMapper;
import com.uit.ooad.scienceresearch.repository.*;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.team.IRegisterTopicService;
import com.uit.ooad.scienceresearch.service.topic.IAddTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Faculty.FACULTY_NOT_FOUND;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Field.FIELD_NOT_FOUND;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Level.LEVEL_NOT_FOUND;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Register.BAD_REQUEST;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Register.EMPTY_REGISTER;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Topic.TOPIC_NOT_FOUND;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class RegisterTopicServiceImpl extends AbstractBaseService<IRegisterTopicService.Input, Boolean>
        implements IRegisterTopicService<IRegisterTopicService.Input, Boolean> {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamLecturerRepository teamLecturerRepository;

    @Autowired
    SignUpTopicRepository signUpTopicRepository;

    @Autowired
    TeamLecturerMapper teamLecturerMapper;

    @Autowired
    SignUpTopicMapper signUpTopicMapper;

    @Override
    public void preExecute(Input input) {
        if (topicRepository.findById(input.getTopicId()).isEmpty()) {
            throw new NotFoundException(messageHelper.getMessage(TOPIC_NOT_FOUND, input.getTopicId()));
        }
        if (input.getListMember().size() == 0) {
            throw new InvalidException(messageHelper.getMessage(EMPTY_REGISTER));
        }
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        List<String> lecturerUsername = input.getListMember()
                .stream()
                .map(TeamLecturerDto::getUsername)
                .collect(Collectors.toList());
        if (!lecturerUsername.contains(userPrincipal.getUsername())) {
            throw new BadRequestException(messageHelper.getMessage(BAD_REQUEST));
        }
        List<Boolean> primaries = input.getListMember()
                .stream()
                .map(TeamLecturerDto::isPrimary)
                .collect(Collectors.toList());
        if (!primaries.contains(true)) {
            throw new BadRequestException(messageHelper.getMessage(BAD_REQUEST));
        }

    }

    @Override
    public Boolean doing(Input input) {

        try {
            List<TeamLecturerDto> listMember = input.getListMember();
            listMember.removeIf(dto -> dto.getUsername() == null);
            // Create a new team
            Team team = new Team();
            team = teamRepository.save(team);
            Lecturer lecturer = new Lecturer();
            // Create member of a team
            for (TeamLecturerDto item : listMember) {
                item.setTeamId(team.getTeamId());
                item.setLecturerId(accountRepository
                        .findByUsername(item.getUsername()).get().getLecturers().get(0).getLecturerId());
                teamLecturerRepository.save(teamLecturerMapper.toEntity(item));
            }

            // Create register a topic of team
            Topic topic = topicRepository.findById(input.getTopicId()).get();
            SignUpTopicDto signUpTopicDto = new SignUpTopicDto();
            signUpTopicDto.setTeamId(team.getTeamId());
            signUpTopicDto.setTopicId(input.getTopicId());
            signUpTopicDto.setStart(EProcess.APPROVE);
            signUpTopicDto.setFacultyReview(EProcess.PROCESSING);
            if (topic.getLevel().getLevelId() == 2) {
                signUpTopicDto.setUniversityReview(EProcess.WAITING);
            } else {
                signUpTopicDto.setUniversityReview(EProcess.NONE);
            }
            signUpTopicDto.setCompleted(EProcess.WAITING);
            signUpTopicRepository.save(signUpTopicMapper.toEntity(signUpTopicDto));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
