package com.uit.ooad.scienceresearch.service.council.impl;

import com.uit.ooad.scienceresearch.constant.EProcess;
import com.uit.ooad.scienceresearch.constant.ERole;
import com.uit.ooad.scienceresearch.data.UserPrincipal;
import com.uit.ooad.scienceresearch.dto.council.CouncilLecturerDto;
import com.uit.ooad.scienceresearch.dto.team.TeamLecturerDto;
import com.uit.ooad.scienceresearch.dto.topic.SignUpTopicDto;
import com.uit.ooad.scienceresearch.entity.Council;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.entity.Team;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.exception.ForbiddenException;
import com.uit.ooad.scienceresearch.exception.InvalidException;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.council.CouncilMapper;
import com.uit.ooad.scienceresearch.repository.AccountRepository;
import com.uit.ooad.scienceresearch.repository.CouncilLecturerRepository;
import com.uit.ooad.scienceresearch.repository.CouncilRepository;
import com.uit.ooad.scienceresearch.repository.TopicRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.council.ICreateCouncilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (userPrincipal.getRoleCode().equals(ERole.MANAGER)){
            throw new ForbiddenException(messageHelper.getMessage(NOT_PERMISSION));
        }
    }

    @Override
    public Boolean doing(Input input) {
        try {
            /*List<CouncilLecturerDto> listMember = input.getListMember();
            listMember.removeIf(dto -> dto.getUsername() == null);
            // Create a new council
            Council council = new Council();
            council = councilRepository.save(council);
            // Create member of a council
            for (CouncilLecturerDto item : listMember) {
                item.setCouncilId(council.getCouncilId());
                item.setLecturerId(accountRepository
                        .findByUsername(item.getUsername()).get().getLecturers().get(0).getLecturerId());
                councilLecturerRepository.save(councilMapper.toEntity(item));
            }

            // Create council review a topic
            Topic topic = topicRepository.findById(input.getTopicId()).get();
            CouncilA signUpTopicDto = new SignUpTopicDto();
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
            signUpTopicRepository.save(signUpTopicMapper.toEntity(signUpTopicDto));*/
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
