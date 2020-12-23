package com.uit.ooad.scienceresearch.service.topic.impl;

import com.uit.ooad.scienceresearch.repository.TopicRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.topic.IAddTopicService;
import com.uit.ooad.scienceresearch.service.topic.ICountTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/9/2020
 */
@Service
public class CountTopicServiceImpl extends AbstractBaseService<ICountTopicService.Input, Long>
        implements ICountTopicService<ICountTopicService.Input, Long> {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Long doing(ICountTopicService.Input input) {
        return topicRepository
                .countCustomerByNameTopicContainingAndFacultyIdAndLevelIdAndFieldId(
                        input.getSearch(), input.getFacultyId(), input.getLevelId(), input.getFieldId(), input.getYear()
                        ,input.getDeleted());
    }
}
