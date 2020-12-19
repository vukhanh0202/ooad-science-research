package com.uit.ooad.scienceresearch.service.topic;

import com.uit.ooad.scienceresearch.dto.topic.TopicDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicFullDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicRegisterDto;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
public interface ITopicService {

    IFindAllTopicService<IFindAllTopicService.Input, List<TopicFullDto>> getFindAllTopicService();

    IFindTopicByIdService<Long, TopicFullDto> getFindTopicByIdService();

    IAddTopicService<TopicDto, Boolean> getAddTopicService();

    IUpdateTopicService<TopicDto, Boolean> getUpdateTopicService();

    IDeleteTopicService<TopicDto, Boolean> getDeleteTopicService();

    ICountTopicService<ICountTopicService.Input, Long> getCountTopicService();

    IFindAllTopicRegisterService<IFindAllTopicRegisterService.Input, List<TopicRegisterDto>> getFindAllTopicRegisterService();

}
