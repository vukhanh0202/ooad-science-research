package com.uit.ooad.scienceresearch.service.council;

import com.uit.ooad.scienceresearch.dto.council.*;
import com.uit.ooad.scienceresearch.dto.field.FieldTopicDto;
import com.uit.ooad.scienceresearch.service.field.IFindAllNameFieldService;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
public interface ICouncilService {

    IFindAllTopicReviewService<IFindAllTopicReviewService.Input, List<TopicReview>> getFindAllTopicReviewService();

    ICreateCouncilService<ICreateCouncilService.Input, Boolean> getCreateCouncilService();

    IFindAllListCouncilService<IFindAllListCouncilService.Input, List<CouncilInfoDto>> getFindAllListCouncilService();

    IFindDetailCouncilService<Long, CouncilFullDto> getFindDetailCouncilService();

    IFindAllReviewCouncilByUserService<Long, List<ReviewCouncilByUserDto>> getFindAllReviewCouncilByUserService();

    IFindDetailReviewCouncilByUserService<IFindDetailReviewCouncilByUserService.Input, DetailReviewCouncilByUserDto> getFindDetailReviewCouncilByUserService();

    IAddReviewCouncilService<RecordDto,Boolean> getAddReviewCouncilService();

    IFindMyCouncilService<Long, List<CouncilInfoDto>>  getFindMyCouncilService();
}
