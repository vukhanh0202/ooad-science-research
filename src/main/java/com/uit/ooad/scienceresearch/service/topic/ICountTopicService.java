package com.uit.ooad.scienceresearch.service.topic;

import com.uit.ooad.scienceresearch.service.IBaseService;
import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/9/2020
 */
public interface ICountTopicService<Input, Output> extends IBaseService<Input, Output> {

    @Data
    class Input{
        String search;
        Long facultyId;
        Long levelId;
        Long fieldId;
        public Input(String search, Long facultyId, Long levelId, Long fieldId) {
            this.search = search;
            this.facultyId = facultyId;
            this.levelId = levelId;
            this.fieldId = fieldId;
        }
    }
}
