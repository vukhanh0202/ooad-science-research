package com.uit.ooad.scienceresearch.service.team;

import com.uit.ooad.scienceresearch.dto.topic.TopicDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicFullDto;
import com.uit.ooad.scienceresearch.service.topic.*;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
public interface ITeamService {

    IRegisterTopicService<IRegisterTopicService.Input,Boolean> getRegisterTopicService();
}
