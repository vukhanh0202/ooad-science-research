package com.uit.ooad.scienceresearch.service.topic.impl;

import com.uit.ooad.scienceresearch.dto.topic.TopicDto;
import com.uit.ooad.scienceresearch.exception.InvalidException;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.topic.TopicMapper;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.repository.FieldTopicRepository;
import com.uit.ooad.scienceresearch.repository.LevelRepository;
import com.uit.ooad.scienceresearch.repository.TopicRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.topic.IAddTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Faculty.FACULTY_NOT_FOUND;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Field.FIELD_NOT_FOUND;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Level.LEVEL_NOT_FOUND;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class AddTopicServiceImpl extends AbstractBaseService<TopicDto, Boolean>
        implements IAddTopicService<TopicDto, Boolean> {

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
        if (fieldTopicRepository.findById(topicDto.getFieldId()).isEmpty()){
            throw new NotFoundException(messageHelper.getMessage(FIELD_NOT_FOUND,topicDto.getTopicId()));
        }
        if (levelRepository.findById(topicDto.getLevelId()).isEmpty()){
            throw new NotFoundException(messageHelper.getMessage(LEVEL_NOT_FOUND,topicDto.getLevelId()));
        }
        if (facultyRepository.findById(topicDto.getFacultyId()).isEmpty()){
            throw new NotFoundException(messageHelper.getMessage(FACULTY_NOT_FOUND,topicDto.getFacultyId()));
        }
    }

    @Override
    public Boolean doing(TopicDto topicDto) {
        try{
            topicDto.setYear((long) LocalDate.now().getYear());
            topicRepository.save(topicMapper.toTopic(topicDto));
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
