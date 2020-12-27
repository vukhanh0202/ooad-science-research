package com.uit.ooad.scienceresearch.service.council.impl;

import com.uit.ooad.scienceresearch.dto.council.ReviewCouncilByUserDto;
import com.uit.ooad.scienceresearch.service.council.*;
import com.uit.ooad.scienceresearch.service.field.IFieldService;
import com.uit.ooad.scienceresearch.service.field.IFindAllNameFieldService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
@Getter
public class CouncilServiceImpl implements ICouncilService {

    @Autowired
    IFindAllTopicReviewService findAllTopicReviewService;

    @Autowired
    ICreateCouncilService createCouncilService;

    @Autowired
    IFindAllListCouncilService findAllListCouncilService;

    @Autowired
    IFindDetailCouncilService findDetailCouncilService;

    @Autowired
    IFindAllReviewCouncilByUserService findAllReviewCouncilByUserService;

    @Autowired
    IFindDetailReviewCouncilByUserService findDetailReviewCouncilByUserService;

    @Autowired
    IAddReviewCouncilService addReviewCouncilService;

    @Autowired
    IFindMyCouncilService findMyCouncilService;
}
