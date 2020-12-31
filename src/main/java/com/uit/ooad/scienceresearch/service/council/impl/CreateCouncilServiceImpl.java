package com.uit.ooad.scienceresearch.service.council.impl;

import com.uit.ooad.scienceresearch.constant.EProcess;
import com.uit.ooad.scienceresearch.constant.ERole;
import com.uit.ooad.scienceresearch.data.UserPrincipal;
import com.uit.ooad.scienceresearch.dto.council.CouncilLecturerDto;
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
import com.uit.ooad.scienceresearch.exception.ForbiddenException;
import com.uit.ooad.scienceresearch.exception.InvalidException;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.council.CouncilMapper;
import com.uit.ooad.scienceresearch.repository.*;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.council.ICreateCouncilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Default.DUPLICATE_USER;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Register.BAD_REQUEST;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Register.EMPTY_REGISTER;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Role.NOT_PERMISSION;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Topic.TOPIC_NOT_FOUND;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/24/2020
 */
@Service
public class CreateCouncilServiceImpl extends AbstractBaseService<ICreateCouncilService.Input, Boolean>
        implements ICreateCouncilService<ICreateCouncilService.Input, Boolean> {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CouncilRepository councilRepository;

    @Autowired
    CouncilLecturerRepository councilLecturerRepository;

    @Autowired
    CouncilMapper councilMapper;

    @Autowired
    SignUpTopicRepository signUpTopicRepository;

    @Autowired
    RecordRepository recordRepository;

    @Override
    public void preExecute(Input input) {
        if (signUpTopicRepository.findAllByTopicTopicId(input.getTopicId()).get(0).getCouncil() != null){
            throw new InvalidException(messageHelper.getMessage(BAD_REQUEST));
        }
        if (topicRepository.findById(input.getTopicId()).isEmpty()) {
            throw new NotFoundException(messageHelper.getMessage(TOPIC_NOT_FOUND, input.getTopicId()));
        }
        if (input.getListMember().size() < 4) {
            throw new InvalidException(messageHelper.getMessage(BAD_REQUEST));
        }
        if (input.getListMember().size() == 0) {
            throw new InvalidException(messageHelper.getMessage(EMPTY_REGISTER));
        }
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        if (!userPrincipal.getRoleCode().equals(ERole.MANAGER)) {
            throw new ForbiddenException(messageHelper.getMessage(NOT_PERMISSION));
        }

        List<CouncilLecturerDto> listInput = input.getListMember();
        listInput.removeIf(dto -> dto.getUsername() == null);
        List<String> usernameList = listInput.stream().map(CouncilLecturerDto::getUsername).collect(Collectors.toList());
        Set<String> sets = new HashSet<>(usernameList);

        if (sets.size() != listInput.size()){
            throw new BadRequestException(messageHelper.getMessage(DUPLICATE_USER));
        }
    }

    @Override
    public Boolean doing(Input input) {
        try {
            List<CouncilLecturerDto> listMember = input.getListMember();
            listMember.removeIf(dto -> dto.getUsername() == null);
            List<Long> idLecturerOfCouncil = new ArrayList<>();
            // Create a new council
            Council council = new Council();
            council = councilRepository.save(council);
            // Create member of a council
            for (CouncilLecturerDto item : listMember) {
                item.setCouncilId(council.getCouncilId());
                item.setLecturerId(accountRepository
                        .findByUsername(item.getUsername()).get().getLecturers().get(0).getLecturerId());
                CouncilLecturer councilLecturer = councilLecturerRepository.save(councilMapper.toEntity(item));
                idLecturerOfCouncil.add(councilLecturer.getLecturer().getLecturerId());
            }

            List<SignUpTopic> signUpTopics = signUpTopicRepository.findAllByTopicTopicId(input.getTopicId());
            // create record of member council
            for (Long item : idLecturerOfCouncil) {
                // Create record follow teams
                for (SignUpTopic signUpTopic : signUpTopics) {
                    // Create council review in sign up topic
                    signUpTopic.setCouncil(council);
                    signUpTopicRepository.save(signUpTopic);
                    // Create record follow teams
                    RecordDto recordDto = new RecordDto();
                    recordDto.setCouncilId(council.getCouncilId());
                    recordDto.setLecturerId(item);
                    recordDto.setTeamId(signUpTopic.getTeam().getTeamId());
                    recordDto.setTopicId(signUpTopic.getTopic().getTopicId());
                    recordRepository.save(councilMapper.toRecordEntity(recordDto));
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
