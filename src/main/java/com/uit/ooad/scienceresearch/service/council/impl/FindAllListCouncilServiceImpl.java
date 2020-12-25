package com.uit.ooad.scienceresearch.service.council.impl;

import com.uit.ooad.scienceresearch.constant.ERole;
import com.uit.ooad.scienceresearch.dto.council.CouncilInfoDto;
import com.uit.ooad.scienceresearch.dto.council.TopicReview;
import com.uit.ooad.scienceresearch.entity.Council;
import com.uit.ooad.scienceresearch.entity.Faculty;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.entity.join.TeamLecturer;
import com.uit.ooad.scienceresearch.exception.ForbiddenException;
import com.uit.ooad.scienceresearch.mapper.council.CouncilMapper;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.repository.LecturerRepository;
import com.uit.ooad.scienceresearch.repository.SignUpTopicRepository;
import com.uit.ooad.scienceresearch.repository.TeamLecturerRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.council.IFindAllListCouncilService;
import com.uit.ooad.scienceresearch.service.council.IFindAllTopicReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Role.NOT_PERMISSION;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class FindAllListCouncilServiceImpl extends AbstractBaseService<IFindAllListCouncilService.Input, List<CouncilInfoDto>>
        implements IFindAllListCouncilService<IFindAllListCouncilService.Input, List<CouncilInfoDto>> {

    @Autowired
    SignUpTopicRepository signUpTopicRepository;

    @Autowired
    CouncilMapper councilMapper;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    TeamLecturerRepository teamLecturerRepository;

    @Override
    public void preExecute(Input input) {

    }

    @Override
    public List<CouncilInfoDto> doing(Input input) {
        try {
            List<SignUpTopic> signUpTopics;
            if (input.getFacultyId() != null) {
                Faculty faculty = facultyRepository.findById(input.getFacultyId()).get();
                List<Long> topicIds = faculty.getTopics().stream().map(Topic::getTopicId).collect(Collectors.toList());
                Set<Long> topicIdsSet = new HashSet<>(topicIds);

                signUpTopics = signUpTopicRepository.findCustomByListTopicId(topicIdsSet);
                signUpTopics.removeIf(signUpTopic -> signUpTopic.getCouncil() == null);
            } else {
                signUpTopics = signUpTopicRepository.findAll();
                signUpTopics.removeIf(signUpTopic -> signUpTopic.getCouncil() == null);
            }

            List<Council> councils = signUpTopics.stream().map(SignUpTopic::getCouncil).collect(Collectors.toList());
            Set<Council> councilsSet = new HashSet<>(councils);

            return councilMapper.toListCouncilInfoDto(councilsSet);
        } catch (Exception e) {
            return null;
        }
    }
}
