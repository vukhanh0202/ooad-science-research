package com.uit.ooad.scienceresearch.service.council.impl;

import com.uit.ooad.scienceresearch.constant.ERole;
import com.uit.ooad.scienceresearch.dto.council.ReviewCouncilByUserDto;
import com.uit.ooad.scienceresearch.dto.council.TopicReview;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.entity.join.Record;
import com.uit.ooad.scienceresearch.exception.ForbiddenException;
import com.uit.ooad.scienceresearch.mapper.council.CouncilMapper;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.repository.RecordRepository;
import com.uit.ooad.scienceresearch.repository.SignUpTopicRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.council.IFindAllReviewCouncilByUserService;
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
public class FindAllReviewCouncilByUserServiceImpl extends AbstractBaseService<Long, List<ReviewCouncilByUserDto>>
        implements IFindAllReviewCouncilByUserService<Long, List<ReviewCouncilByUserDto>> {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    CouncilMapper councilMapper;

    @Override
    public List<ReviewCouncilByUserDto> doing(Long lecturerId) {
        try {
            List<Record> result = recordRepository.findAllByLecturerLecturerId(lecturerId);
            return councilMapper.toListReviewCouncilByUserDto(result);
        } catch (Exception e) {
            return null;
        }
    }
}
