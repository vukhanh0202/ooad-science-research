package com.uit.ooad.scienceresearch.service.council.impl;

import com.uit.ooad.scienceresearch.dto.council.CouncilInfoDto;
import com.uit.ooad.scienceresearch.entity.Council;
import com.uit.ooad.scienceresearch.entity.Faculty;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.entity.join.CouncilLecturer;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.mapper.council.CouncilMapper;
import com.uit.ooad.scienceresearch.repository.CouncilLecturerRepository;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.repository.SignUpTopicRepository;
import com.uit.ooad.scienceresearch.repository.TeamLecturerRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.council.IFindAllListCouncilService;
import com.uit.ooad.scienceresearch.service.council.IFindMyCouncilService;
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
public class FindMyCouncilServiceImpl extends AbstractBaseService<Long, List<CouncilInfoDto>>
        implements IFindMyCouncilService<Long, List<CouncilInfoDto>> {

    @Autowired
    CouncilLecturerRepository councilLecturerRepository;

    @Autowired
    CouncilMapper councilMapper;

    @Override
    public List<CouncilInfoDto> doing(Long lecturerId) {
        try {
            List<CouncilLecturer> councilLecturers = councilLecturerRepository.findAllByLecturerLecturerId(lecturerId);
            List<Council> councils = councilLecturers.stream().map(CouncilLecturer::getCouncil).collect(Collectors.toList());
            Set<Council> councilsSet = new HashSet<>(councils);

            return councilMapper.toListCouncilInfoDto(councilsSet);
        } catch (Exception e) {
            return null;
        }
    }
}
