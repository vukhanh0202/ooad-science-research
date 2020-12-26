package com.uit.ooad.scienceresearch.service.council;

import com.uit.ooad.scienceresearch.service.IBaseService;
import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/13/2020
 */

public interface IFindDetailReviewCouncilByUserService<Input, Output> extends IBaseService<Input, Output> {

    @Data
    class Input {
        Long councilId;
        Long lecturerId;
        Long teamId;
        Long topicId;

        public Input(Long councilId, Long lecturerId, Long teamId, Long topicId) {
            this.councilId = councilId;
            this.lecturerId = lecturerId;
            this.teamId = teamId;
            this.topicId = topicId;
        }
    }
}
