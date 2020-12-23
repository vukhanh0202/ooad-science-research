package com.uit.ooad.scienceresearch.service.topic;

import com.uit.ooad.scienceresearch.payload.PaginationRequest;
import com.uit.ooad.scienceresearch.service.IBaseService;
import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
public interface IFindAllTopicService<Input, Output> extends IBaseService<Input, Output> {

    @Data
    class Input extends PaginationRequest {
        String search;
        Long facultyId;
        Long levelId;
        Long fieldId;
        Long year;
        Boolean deleted;

        public Input(Integer page, Integer size) {
            super(page, size);
        }

        public Input(String search, Long facultyId, Long levelId, Long fieldId, Long year, Boolean deleted, Integer page, Integer size) {
            super(page, size);
            this.search = search;
            this.facultyId = facultyId;
            this.levelId = levelId;
            this.fieldId = fieldId;
            this.year = year;
            this.deleted = deleted;
        }
    }
}
