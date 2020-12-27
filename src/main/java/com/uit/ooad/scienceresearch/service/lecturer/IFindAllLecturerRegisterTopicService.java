package com.uit.ooad.scienceresearch.service.lecturer;

import com.uit.ooad.scienceresearch.payload.PaginationRequest;
import com.uit.ooad.scienceresearch.service.IBaseService;
import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
public interface IFindAllLecturerRegisterTopicService<Input, Output> extends IBaseService<Input, Output> {

    @Data
    class Input {
        String search;
        Long topicId;


        public Input(String search, Long topicId) {
            this.search = search;
            this.topicId = topicId;
        }
    }
}
