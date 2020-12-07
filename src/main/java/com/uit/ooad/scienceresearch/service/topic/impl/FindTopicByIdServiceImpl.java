package com.uit.ooad.scienceresearch.service.topic.impl;

import com.uit.ooad.scienceresearch.dto.topic.TopicFullDto;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.topic.TopicMapper;
import com.uit.ooad.scienceresearch.repository.TopicRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.topic.IFindAllTopicService;
import com.uit.ooad.scienceresearch.service.topic.IFindTopicByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class FindTopicByIdServiceImpl extends AbstractBaseService<Long, TopicFullDto>
        implements IFindTopicByIdService<Long, TopicFullDto> {


    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TopicMapper topicMapper;

    @Override
    public void preExecute(Long topicId) {
        if (topicRepository.findById(topicId).isEmpty()){
            throw new NotFoundException("Topic Not Found!");
        }
    }

    @Override
    public TopicFullDto doing(Long topicId) {
        try{
            return topicMapper.toTopicFullDto(topicRepository.findById(topicId).get());
        }catch (Exception e){
            return null;
        }
    }
}
