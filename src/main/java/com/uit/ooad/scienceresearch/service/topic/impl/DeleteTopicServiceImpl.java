package com.uit.ooad.scienceresearch.service.topic.impl;

import com.uit.ooad.scienceresearch.dto.topic.TopicDto;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.repository.TopicRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.topic.IDeleteTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Topic.TOPIC_NOT_FOUND;

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
            throw new NotFoundException(messageHelper.getMessage(TOPIC_NOT_FOUND,topicDto.getTopicId()));
        }
    }

    @Override
    public Boolean doing(TopicDto topicDto) {
        try{
            Topic topic = topicRepository.findById(topicDto.getTopicId()).get();
            topic.setDeleted(true);
            topicRepository.save(topic);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
