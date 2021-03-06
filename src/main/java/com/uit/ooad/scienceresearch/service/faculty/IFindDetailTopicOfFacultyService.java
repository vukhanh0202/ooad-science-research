package com.uit.ooad.scienceresearch.service.faculty;

import com.uit.ooad.scienceresearch.payload.PaginationRequest;
import com.uit.ooad.scienceresearch.service.IBaseService;
import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
public interface IFindDetailTopicOfFacultyService<Input, Output> extends IBaseService<Input, Output> {

    @Data
    class Input{
        Long topicId;

        public Input(Long topicId) {
            this.topicId = topicId;
        }
    }
}
