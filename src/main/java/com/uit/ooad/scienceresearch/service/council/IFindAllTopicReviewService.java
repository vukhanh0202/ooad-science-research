package com.uit.ooad.scienceresearch.service.council;

import com.uit.ooad.scienceresearch.constant.ERole;
import com.uit.ooad.scienceresearch.service.IBaseService;
import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/13/2020
 */

public interface IFindAllTopicReviewService<Input, Output> extends IBaseService<Input, Output> {

    @Data
    class Input {
        Long facultyId;
        ERole roleCode;

        public Input(Long facultyId, ERole roleCode) {
            this.facultyId = facultyId;
            this.roleCode = roleCode;
        }
    }
}
