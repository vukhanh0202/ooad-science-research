package com.uit.ooad.scienceresearch.service.team.impl;

import com.uit.ooad.scienceresearch.constant.EProcess;
import com.uit.ooad.scienceresearch.data.UserPrincipal;
import com.uit.ooad.scienceresearch.dto.council.RecordDto;
import com.uit.ooad.scienceresearch.dto.team.TeamLecturerDto;
import com.uit.ooad.scienceresearch.dto.topic.SignUpTopicDto;
import com.uit.ooad.scienceresearch.entity.Council;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.entity.Team;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.entity.join.CouncilLecturer;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.exception.BadRequestException;
import com.uit.ooad.scienceresearch.exception.InvalidException;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.council.CouncilMapper;
import com.uit.ooad.scienceresearch.mapper.team.TeamLecturerMapper;
import com.uit.ooad.scienceresearch.mapper.topic.SignUpTopicMapper;
import com.uit.ooad.scienceresearch.repository.*;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.team.IRegisterTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Default.DUPLICATE_USER;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Default.USER_BELONG_TO;
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

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    CouncilMapper councilMapper;


    @Override
    public void preExecute(Input input) {
        if (topicRepository.findById(input.getTopicId()).isEmpty()) {
            throw new NotFoundException(messageHelper.getMessage(TOPIC_NOT_FOUND, input.getTopicId()));
        }

        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        List<String> lecturerUsername = input.getListMember()
                .stream()
                .map(TeamLecturerDto::getUsername)
                .collect(Collectors.toList());
        if (!lecturerUsername.contains(userPrincipal.getUsername())) {
            throw new BadRequestException(messageHelper.getMessage(USER_BELONG_TO));
        }
        List<Boolean> primaries = input.getListMember()
                .stream()
                .map(TeamLecturerDto::isPrimary)
                .collect(Collectors.toList());
        if (!primaries.contains(true)) {
            throw new BadRequestException(messageHelper.getMessage(BAD_REQUEST));
        }
        List<TeamLecturerDto> listInput = input.getListMember();
        listInput.removeIf(dto -> dto.getUsername() == null);

        List<String> usernameList = listInput.stream().map(TeamLecturerDto::getUsername).collect(Collectors.toList());
        Set<String> sets = new HashSet<>(usernameList);

        if (sets.size() != listInput.size()){
            throw new BadRequestException(messageHelper.getMessage(DUPLICATE_USER));
        }
        if (listInput.size() == 0) {
            throw new InvalidException(messageHelper.getMessage(EMPTY_REGISTER));
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
            signUpTopicDto.setStart(EProcess.finish);
            signUpTopicDto.setFacultyReview(EProcess.process);
            if (topic.getLevel().getLevelId() == 2) {
                signUpTopicDto.setUniversityReview(EProcess.wait);
            } else {
                signUpTopicDto.setUniversityReview(EProcess.none);
            }
            signUpTopicDto.setCompleted(EProcess.wait);
            signUpTopicDto.setDateApproved("NOT APPROVE");
            signUpTopicDto.setDateExpired("NOT APPROVE");
            signUpTopicDto.setDateExtend("0");
            signUpTopicDto.setFinish(false);
            List<SignUpTopic> signUpTopics = signUpTopicRepository.findAllByTopicTopicId(input.getTopicId());
            if (signUpTopics.size() > 0) {
                Council council = signUpTopics.get(0).getCouncil();
                if (council != null) {
                    signUpTopicDto.setCouncilId(council.getCouncilId());
                    List<Long> idLecturerOfCouncil = council.getCouncilLecturers()
                            .stream()
                            .map(councilLecturer -> councilLecturer.getLecturer().getLecturerId())
                            .collect(Collectors.toList());
                    for (Long item : idLecturerOfCouncil) {
                        // Create record follow teams
                        RecordDto recordDto = new RecordDto();
                        recordDto.setCouncilId(council.getCouncilId());
                        recordDto.setLecturerId(item);
                        recordDto.setTeamId(team.getTeamId());
                        recordDto.setTopicId(input.getTopicId());
                        recordRepository.save(councilMapper.toRecordEntity(recordDto));
                    }
                }
            }
            signUpTopicRepository.save(signUpTopicMapper.toEntity(signUpTopicDto));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
