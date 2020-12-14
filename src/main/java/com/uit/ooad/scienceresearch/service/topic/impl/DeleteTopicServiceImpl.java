package com.uit.ooad.scienceresearch.service.topic.impl;

import com.uit.ooad.scienceresearch.dto.topic.TopicDto;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.repository.TopicRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.topic.IDeleteTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/8/2020
 */
@Service
public class DeleteTopicServiceImpl extends AbstractBaseService<TopicDto, Boolean>
        implements IDeleteTopicService<TopicDto, Boolean> {

    @Autowired
    TopicRepository topicRepository;

    @Override
    public void preExecute(TopicDto topicDto) {
        if (topicRepository.findById(topicDto.getTopicId()).isEmpty()){
            throw new NotFoundException("Topic Not Found!");
        }
    }

    @Override
    public Boolean doing(TopicDto topicDto) {
        try{
            Topic topic = topicRepository.findById(topicDto.getTopicId()).get();
            topicRepository.delete(topic);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
