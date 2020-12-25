package com.uit.ooad.scienceresearch.service.council.impl;

import com.uit.ooad.scienceresearch.dto.council.CouncilFullDto;
import com.uit.ooad.scienceresearch.dto.council.CouncilInfoDto;
import com.uit.ooad.scienceresearch.entity.Council;
import com.uit.ooad.scienceresearch.entity.Faculty;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.mapper.council.CouncilMapper;
import com.uit.ooad.scienceresearch.repository.CouncilRepository;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.repository.SignUpTopicRepository;
import com.uit.ooad.scienceresearch.repository.TeamLecturerRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.council.IFindAllListCouncilService;
import com.uit.ooad.scienceresearch.service.council.IFindDetailCouncilService;
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
public class FindDetailServiceImpl extends AbstractBaseService<Long, CouncilFullDto>
        implements IFindDetailCouncilService<Long, CouncilFullDto> {

    @Autowired
    SignUpTopicRepository signUpTopicRepository;

    @Autowired
    CouncilMapper councilMapper;

    @Autowired
    CouncilRepository councilRepository;

    @Override
    public CouncilFullDto doing(Long councilId) {
        try {
            CouncilFullDto result = new CouncilFullDto();
            result.setMembers(councilMapper.toListDto(councilRepository.findById(councilId).get().getCouncilLecturers()));
            result.setReviewList(councilMapper.toListTopicReviewByCouncilDto(signUpTopicRepository.findAllByCouncil_CouncilId(councilId)));
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
