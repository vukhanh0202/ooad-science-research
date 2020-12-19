package com.uit.ooad.scienceresearch.service.team;

import com.uit.ooad.scienceresearch.dto.team.TeamLecturerDto;
import com.uit.ooad.scienceresearch.service.IBaseService;
import lombok.Data;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/19/2020
 */
public interface IRegisterTopicService<Input, Output> extends IBaseService<Input, Output> {

    @Data
    class Input{
        Long topicId;
        List<TeamLecturerDto> listMember;
        public Input(Long topicId, List<TeamLecturerDto> listMember) {
            this.topicId = topicId;
            this.listMember = listMember;
        }
    }
}
