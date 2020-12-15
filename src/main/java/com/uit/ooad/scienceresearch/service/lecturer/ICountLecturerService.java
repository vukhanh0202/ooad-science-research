package com.uit.ooad.scienceresearch.service.lecturer;

import com.uit.ooad.scienceresearch.payload.PaginationRequest;
import com.uit.ooad.scienceresearch.service.IBaseService;
import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/9/2020
 */
public interface ICountLecturerService<Input, Output> extends IBaseService<Input, Output> {

    @Data
    class Input extends PaginationRequest {
        String search;
        Long facultyId;
        Long contractId;

        public Input(Integer page, Integer size) {
            super(page, size);
        }

        public Input(String search, Long facultyId, Long contractId, Integer page, Integer size) {
            super(page, size);
            this.search = search;
            this.facultyId = facultyId;
            this.contractId = contractId;
        }
    }
}
