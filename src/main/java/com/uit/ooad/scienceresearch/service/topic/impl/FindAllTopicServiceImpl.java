package com.uit.ooad.scienceresearch.service.topic.impl;

import com.uit.ooad.scienceresearch.dto.topic.TopicDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicFullDto;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.topic.TopicMapper;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.repository.FieldTopicRepository;
import com.uit.ooad.scienceresearch.repository.LevelRepository;
import com.uit.ooad.scienceresearch.repository.TopicRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.topic.IAddTopicService;
import com.uit.ooad.scienceresearch.service.topic.IFindAllTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class FindAllTopicServiceImpl extends AbstractBaseService<IFindAllTopicService.Input, List<TopicFullDto>>
        implements IFindAllTopicService<IFindAllTopicService.Input, List<TopicFullDto>> {


    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TopicMapper topicMapper;


    @Override
    public List<TopicFullDto> doing(IFindAllTopicService.Input input) {
        try {
            if (input.getSearch().equals("")) {
                return topicMapper
                        .toListTopicFullDto(topicRepository
                                .findAll(input.createPageable(Sort.Direction.ASC, "id"))
                                .getContent());
            } else {
                return topicMapper
                        .toListTopicFullDto(topicRepository
                                .findAllByNameTopicContaining(input.getSearch(),
                                        input.createPageable(Sort.Direction.ASC, "id")));
            }
        } catch (Exception e) {
            return null;
        }
    }
}
