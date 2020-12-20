package com.uit.ooad.scienceresearch.service.topic;

import com.uit.ooad.scienceresearch.constant.ERole;
import com.uit.ooad.scienceresearch.service.IBaseService;
import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
public interface IApproveTopicService<Input, Output> extends IBaseService<Input, Output> {
    @Data
    class Input {
        Long topicId;
        Long teamId;
        ERole roleCode;

        public Input(Long topicId, Long teamId, ERole roleCode) {
            this.topicId = topicId;
            this.teamId = teamId;
            this.roleCode = roleCode;
        }
    }
}
