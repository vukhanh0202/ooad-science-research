package com.uit.ooad.scienceresearch.service.topic;

import com.uit.ooad.scienceresearch.dto.topic.TopicDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicFullDto;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
public interface ITopicService {

    IFindAllTopicService<Void, List<TopicFullDto>> getFindAllTopicService();

    IFindTopicByIdService<Long, TopicFullDto> getFindTopicByIdService();

    IAddTopicService<TopicDto, Boolean> getAddTopicService();

    IUpdateTopicService<TopicDto, Boolean> getUpdateTopicService();

    IDeleteTopicService<TopicDto, Boolean> getDeleteTopicService();

}
