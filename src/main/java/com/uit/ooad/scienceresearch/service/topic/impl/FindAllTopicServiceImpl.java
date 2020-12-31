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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Faculty.FACULTY_NOT_FOUND;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Field.FIELD_NOT_FOUND;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Level.LEVEL_NOT_FOUND;

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

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    LevelRepository levelRepository;

    @Autowired
    FieldTopicRepository fieldTopicRepository;


    @Override
    public void preExecute(Input input) {
        if (input.getFacultyId() != null) {
            if (facultyRepository.findById(input.getFacultyId()).isEmpty()) {
                throw new NotFoundException(messageHelper.getMessage(FACULTY_NOT_FOUND, input.getFacultyId()));
            }
        }
        if (input.getFieldId() != null) {
            if (fieldTopicRepository.findById(input.getFieldId()).isEmpty()) {
                throw new NotFoundException(messageHelper.getMessage(FIELD_NOT_FOUND, input.getFacultyId()));
            }
        }
        if (input.getLevelId() != null) {
            if (levelRepository.findById(input.getLevelId()).isEmpty()) {
                throw new NotFoundException(messageHelper.getMessage(LEVEL_NOT_FOUND, input.getFacultyId()));
            }
        }
    }

    @Override
    public List<TopicFullDto> doing(IFindAllTopicService.Input input) {
        try {
            return topicMapper.toListTopicFullDto(topicRepository
                    .findCustomerByNameTopicContainingAndFacultyIdAndLevelIdAndFieldId(input.getSearch(),
                            input.getFacultyId(), input.getLevelId(), input.getFieldId(), input.getYear(),input.getDeleted(),
                            input.createPageable(Sort.Direction.ASC, "name_topic")));
        } catch (Exception e) {
            return null;
        }
    }
}
