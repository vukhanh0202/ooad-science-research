package com.uit.ooad.scienceresearch.service.lecturer.impl;

import com.uit.ooad.scienceresearch.dto.account.AccountLecturerSearchDto;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.entity.join.CouncilLecturer;
import com.uit.ooad.scienceresearch.entity.join.Record;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.mapper.account.AccountMapper;
import com.uit.ooad.scienceresearch.repository.AccountRepository;
import com.uit.ooad.scienceresearch.repository.LecturerRepository;
import com.uit.ooad.scienceresearch.repository.SignUpTopicRepository;
import com.uit.ooad.scienceresearch.repository.TeamLecturerRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.lecturer.IFindAllLecturerCreateCouncilTopicService;
import com.uit.ooad.scienceresearch.service.lecturer.IFindAllLecturerRegisterTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class FindAllLecturerCreateCouncilTopicServiceImpl extends AbstractBaseService<IFindAllLecturerCreateCouncilTopicService.Input, List<AccountLecturerSearchDto>>
        implements IFindAllLecturerCreateCouncilTopicService<IFindAllLecturerCreateCouncilTopicService.Input, List<AccountLecturerSearchDto>> {


    @Autowired
    AccountMapper accountMapper;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    LecturerRepository lecturerRepository;

    @Autowired
    TeamLecturerRepository teamLecturerRepository;

    @Autowired
    SignUpTopicRepository signUpTopicRepository;

    @Override
    public List<AccountLecturerSearchDto> doing(Input input) {
        try {
            List<AccountLecturerSearchDto> result = accountMapper
                    .toListAccountLecturerSearchDto(accountRepository.findAllByUsernameContaining(input.getSearch()));
            Set<Long> listIdLecturerInvalid = new HashSet<>();
            List<Lecturer> lecturers = lecturerRepository.findAll();

            for (Lecturer lecturer : lecturers) {
                List<Long> teams = teamLecturerRepository.findAllByLecturerLecturerId(lecturer.getLecturerId())
                        .stream()
                        .map(teamLecturer -> teamLecturer.getTeam().getTeamId())
                        .collect(Collectors.toList());
                Set<Long> teamsSet = new HashSet<>(teams);
                List<Long> signUpTopics = signUpTopicRepository
                        .findCustomByListTeamId(teamsSet)
                        .stream()
                        .map(signUpTopic -> signUpTopic.getTopic().getTopicId())
                        .collect(Collectors.toList());
                if (signUpTopics.contains(input.getTopicId())) {
                    listIdLecturerInvalid.add(lecturer.getLecturerId());
                }
            }
            for (Long idRemove : listIdLecturerInvalid) {
                result.removeIf(rs -> rs.getLecturerId().equals(idRemove));
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
