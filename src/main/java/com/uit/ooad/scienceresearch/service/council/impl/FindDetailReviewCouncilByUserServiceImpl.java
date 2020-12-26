package com.uit.ooad.scienceresearch.service.council.impl;

import com.uit.ooad.scienceresearch.dto.council.DetailReviewCouncilByUserDto;
import com.uit.ooad.scienceresearch.dto.council.ReviewCouncilByUserDto;
import com.uit.ooad.scienceresearch.entity.join.Record;
import com.uit.ooad.scienceresearch.mapper.council.CouncilMapper;
import com.uit.ooad.scienceresearch.repository.RecordRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.council.IFindAllReviewCouncilByUserService;
import com.uit.ooad.scienceresearch.service.council.IFindDetailReviewCouncilByUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class FindDetailReviewCouncilByUserServiceImpl extends
        AbstractBaseService<IFindDetailReviewCouncilByUserService.Input, DetailReviewCouncilByUserDto>
        implements IFindDetailReviewCouncilByUserService<IFindDetailReviewCouncilByUserService.Input, DetailReviewCouncilByUserDto> {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    CouncilMapper councilMapper;

    @Override
    public DetailReviewCouncilByUserDto doing(Input input) {
        try {
            Record record = recordRepository
                    .findAllByCouncil_CouncilIdAndLecturerLecturerIdAndTeamTeamIdAndTopicTopicId(input.getCouncilId(),
                            input.getLecturerId(), input.getTeamId(), input.getTopicId());
            return councilMapper.toDetailReviewCouncilByUserDto(record);
        } catch (Exception e) {
            return null;
        }
    }
}
