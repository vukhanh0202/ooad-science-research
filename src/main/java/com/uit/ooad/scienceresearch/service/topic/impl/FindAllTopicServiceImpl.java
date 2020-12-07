package com.uit.ooad.scienceresearch.service.topic.impl;

import com.uit.ooad.scienceresearch.dto.topic.TopicDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicFullDto;
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
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class FindAllTopicServiceImpl extends AbstractBaseService<Void, List<TopicFullDto>>
        implements IFindAllTopicService<Void, List<TopicFullDto>> {


    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TopicMapper topicMapper;


    @Override
    public List<TopicFullDto> doing(Void aVoid) {
        try{
            return topicMapper.toListTopicFullDto(topicRepository.findAll());
        }catch (Exception e){
            return null;
        }
    }
}
