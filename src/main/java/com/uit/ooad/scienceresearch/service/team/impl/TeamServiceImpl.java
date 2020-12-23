package com.uit.ooad.scienceresearch.service.team.impl;

import com.uit.ooad.scienceresearch.service.team.IFindMemberOfTeamService;
import com.uit.ooad.scienceresearch.service.team.IRegisterTopicService;
import com.uit.ooad.scienceresearch.service.team.ITeamService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/19/2020
 */
@Service
@Getter
public class TeamServiceImpl implements ITeamService {

    @Autowired
    IRegisterTopicService registerTopicService;

    @Autowired
    IFindMemberOfTeamService findMemberOfTeamService;
}
