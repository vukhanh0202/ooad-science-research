package com.uit.ooad.scienceresearch.service.lecturer.impl;

import com.uit.ooad.scienceresearch.dto.account.AccountLecturerSearchDto;
import com.uit.ooad.scienceresearch.dto.lecturer.LecturerFullDto;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.entity.join.CouncilLecturer;
import com.uit.ooad.scienceresearch.entity.join.Record;
import com.uit.ooad.scienceresearch.entity.join.TeamLecturer;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.account.AccountMapper;
import com.uit.ooad.scienceresearch.mapper.lecturer.LecturerMapper;
import com.uit.ooad.scienceresearch.repository.AccountRepository;
import com.uit.ooad.scienceresearch.repository.ContractRepository;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.repository.LecturerRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.lecturer.IFindAllLecturerRegisterTopicService;
import com.uit.ooad.scienceresearch.service.lecturer.IFindAllLecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Contract.CONTRACT_NOT_FOUND;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Faculty.FACULTY_NOT_FOUND;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class FindAllLecturerRegisterTopicServiceImpl extends AbstractBaseService<IFindAllLecturerRegisterTopicService.Input, List<AccountLecturerSearchDto>>
        implements IFindAllLecturerRegisterTopicService<IFindAllLecturerRegisterTopicService.Input, List<AccountLecturerSearchDto>> {


    @Autowired
    AccountMapper accountMapper;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    LecturerRepository lecturerRepository;

    @Override
    public List<AccountLecturerSearchDto> doing(Input input) {
        try {
            List<AccountLecturerSearchDto> result = accountMapper
                    .toListAccountLecturerSearchDto(accountRepository.findAllByUsernameContaining(input.getSearch()));
            Set<Long> listIdLecturerInvalid = new HashSet<>();
            List<Lecturer> lecturers = lecturerRepository.findAll();

            for (Lecturer lecturer : lecturers) {
                List<TeamLecturer> teams = lecturer.getGroupLecturers();
                List<CouncilLecturer> councils = lecturer.getCouncilLecturers();
                // Remove lecturer register > 3 topic
                for (TeamLecturer team : teams) {
                    if (team.getTeam().getSignUpTopics().size() > 3) {
                        listIdLecturerInvalid.add(lecturer.getLecturerId());
                        continue;
                    }
                }
                // Remove lecturer exist in council review this topic
                for (CouncilLecturer council : councils) {
                    List<Record> listRecord = council.getCouncil().getRecords();
                    listRecord.removeIf(record -> !record.getTopic().getTopicId().equals(input.getTopicId()));
                    if (listRecord.size() > 0) {
                        listIdLecturerInvalid.add(lecturer.getLecturerId());
                        continue;
                    }
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
