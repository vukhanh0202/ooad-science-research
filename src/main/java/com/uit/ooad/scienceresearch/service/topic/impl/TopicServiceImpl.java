package com.uit.ooad.scienceresearch.service.topic.impl;

import com.uit.ooad.scienceresearch.service.topic.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
@Getter
public class TopicServiceImpl implements ITopicService {

    @Autowired
    private IFindAllTopicService findAllTopicService;

    @Autowired
    private IFindTopicByIdService findTopicByIdService;

    @Autowired
    private IAddTopicService addTopicService;

    @Autowired
    private IUpdateTopicService updateTopicService;

    @Autowired
    private IDeleteTopicService deleteTopicService;

    @Autowired
    private ICountTopicService countTopicService;

    @Autowired
    private IFindAllTopicRegisterService findAllTopicRegisterService;

    @Autowired
    private IFindAllTopicAssignService findAllTopicAssignService;

    @Autowired
    private IFindDetailTopicRegisterService findDetailTopicRegisterService;

    @Autowired
    private IApproveTopicService approveTopicService;

    @Autowired
    private IDeclineTopicService declineTopicService;

    @Autowired
    private IUpdateMyTopicService updateMyTopicService;
}
