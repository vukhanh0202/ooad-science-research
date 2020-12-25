package com.uit.ooad.scienceresearch.service.council;

import com.uit.ooad.scienceresearch.dto.council.CouncilLecturerDto;
import com.uit.ooad.scienceresearch.service.IBaseService;
import lombok.Data;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/24/2020
 */
public interface ICreateCouncilService<Input, Output> extends IBaseService<Input, Output> {

    @Data
    class Input{
        Long topicId;
        List<CouncilLecturerDto> listMember;
        public Input(Long topicId, List<CouncilLecturerDto> listMember) {
            this.topicId = topicId;
            this.listMember = listMember;
        }
    }
}