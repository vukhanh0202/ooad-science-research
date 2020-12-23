package com.uit.ooad.scienceresearch.service.team.impl;

import com.uit.ooad.scienceresearch.dto.team.TeamLecturerDto;
import com.uit.ooad.scienceresearch.mapper.team.TeamLecturerMapper;
import com.uit.ooad.scienceresearch.repository.TeamRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.team.IFindMemberOfTeamService;
import com.uit.ooad.scienceresearch.service.team.IRegisterTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/23/2020
 */
@Service
public class FindMemberOfTeamServiceImpl extends AbstractBaseService<Long, List<TeamLecturerDto>>
        implements IFindMemberOfTeamService<Long, List<TeamLecturerDto>> {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamLecturerMapper teamLecturerMapper;

    @Override
    public List<TeamLecturerDto> doing(Long teamId) {
        return teamLecturerMapper.toListTeamLecturerDto(teamRepository.findById(teamId).get().getGroupLecturers());
    }
}
