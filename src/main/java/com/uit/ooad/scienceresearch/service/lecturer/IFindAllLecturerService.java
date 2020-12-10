package com.uit.ooad.scienceresearch.service.lecturer;

import com.uit.ooad.scienceresearch.payload.PaginationRequest;
import com.uit.ooad.scienceresearch.service.IBaseService;
import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
public interface IFindAllLecturerService<Input, Output> extends IBaseService<Input, Output> {

    @Data
    class Input extends PaginationRequest {
        String search;

        public Input(Integer page, Integer size) {
            super(page, size);
        }

        public Input(String search, Integer page, Integer size) {
            super(page, size);
            this.search = search;
        }
    }
}
