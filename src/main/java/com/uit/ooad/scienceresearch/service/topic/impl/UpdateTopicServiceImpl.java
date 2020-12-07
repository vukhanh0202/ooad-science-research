package com.uit.ooad.scienceresearch.service.topic.impl;

import com.uit.ooad.scienceresearch.dto.topic.TopicDto;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.topic.TopicMapper;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.repository.FieldTopicRepository;
import com.uit.ooad.scienceresearch.repository.LevelRepository;
import com.uit.ooad.scienceresearch.repository.TopicRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.topic.IAddTopicService;
import com.uit.ooad.scienceresearch.service.topic.IUpdateTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class UpdateTopicServiceImpl extends AbstractBaseService<TopicDto, Boolean>
        implements IUpdateTopicService<TopicDto, Boolean> {

    @Autowired
    FieldTopicRepository fieldTopicRepository;

    @Autowired
    LevelRepository levelRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TopicMapper topicMapper;

    @Override
    public void preExecute(TopicDto topicDto) {
        if (topicRepository.findById(topicDto.getId()).isEmpty()){
            throw new NotFoundException("Topic Not Found!");
        }
        if (fieldTopicRepository.findById(topicDto.getField_id()).isEmpty()){
            throw new NotFoundException("Field Topic Not Found!");
        }
        if (levelRepository.findById(topicDto.getLevel_id()).isEmpty()){
            throw new NotFoundException("Level Not Found!");
        }
        if (facultyRepository.findById(topicDto.getFaculty_id()).isEmpty()){
            throw new NotFoundException("Faculty Not Found!");
        }
    }

    @Override
    public Boolean doing(TopicDto topicDto) {
        try{
            Topic oldTopic = topicRepository.findById(topicDto.getId()).get();
            topicMapper.updateTopic(topicDto, oldTopic);
            topicRepository.save(oldTopic);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
