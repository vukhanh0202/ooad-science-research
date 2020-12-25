package com.uit.ooad.scienceresearch.service.council.impl;

import com.uit.ooad.scienceresearch.constant.ERole;
import com.uit.ooad.scienceresearch.dto.council.TopicReview;
import com.uit.ooad.scienceresearch.dto.field.FieldTopicDto;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.exception.ForbiddenException;
import com.uit.ooad.scienceresearch.mapper.council.CouncilMapper;
import com.uit.ooad.scienceresearch.mapper.field.FieldTopicMapper;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.repository.FieldTopicRepository;
import com.uit.ooad.scienceresearch.repository.SignUpTopicRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.council.IFindAllTopicReviewService;
import com.uit.ooad.scienceresearch.service.field.IFindAllNameFieldService;
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
public class FindAllTopicReviewServiceImpl extends AbstractBaseService<IFindAllTopicReviewService.Input, List<TopicReview>>
        implements IFindAllTopicReviewService<IFindAllTopicReviewService.Input, List<TopicReview>> {

    @Autowired
    SignUpTopicRepository signUpTopicRepository;

    @Autowired
    CouncilMapper councilMapper;

    @Autowired
    FacultyRepository facultyRepository;

    @Override
    public void preExecute(Input input) {
        if (input.getRoleCode() != ERole.MANAGER) {
            throw new ForbiddenException(messageHelper.getMessage(NOT_PERMISSION));
        }
    }

    @Override
    public List<TopicReview> doing(Input input) {
        try {
            List<Long> idTopicList = facultyRepository.findById(input.getFacultyId()).get()
                    .getTopics().stream().map(Topic::getTopicId).collect(Collectors.toList());
            Set<Long> idTopicSet = new HashSet<>(idTopicList);
            return councilMapper.toListTopicReview(signUpTopicRepository.findCustomByListTopicIdAndFinish(idTopicSet, true));
        } catch (Exception e) {
            return null;
        }
    }
}
