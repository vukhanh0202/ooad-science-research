package com.uit.ooad.scienceresearch.mapper.team;

import com.uit.ooad.scienceresearch.dto.team.TeamLecturerDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicFullDto;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.entity.join.TeamLecturer;
import com.uit.ooad.scienceresearch.mapper.BaseMapper;
import com.uit.ooad.scienceresearch.mapper.team.converter.TeamLecturerConverter;
import com.uit.ooad.scienceresearch.mapper.topic.converter.TopicConverter;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Mapper(componentModel = "spring")
@Component
public abstract class TeamLecturerMapper implements BaseMapper {

    @Autowired
    TeamLecturerConverter teamLecturerConverter;

    @Named("toEntity")
    @BeforeMapping
    protected void toEntity(TeamLecturerDto dto, @MappingTarget TeamLecturer entity) {
        entity.setLecturer(teamLecturerConverter.getLecturer(dto.getLecturerId()));
        entity.setTeam(teamLecturerConverter.getTeam(dto.getTeamId()));
    }

    @Named("toDto")
    @BeforeMapping
    protected void toDto(TeamLecturer entity, @MappingTarget TeamLecturerDto dto) {
        dto.setFullName(entity.getLecturer().getFullName());
    }

    @BeanMapping(qualifiedByName = "toEntity", ignoreByDefault = true)
    @Mapping(source = "position", target = "position")
    @Mapping(source = "primary", target = "primary")
    public abstract TeamLecturer toEntity(TeamLecturerDto dto);

    @BeanMapping(qualifiedByName = "toDto", ignoreByDefault = true)
    @Mapping(source = "position", target = "position")
    @Mapping(source = "primary", target = "primary")
    public abstract TeamLecturerDto toTeamLecturerDto(TeamLecturer entity);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<TeamLecturerDto> toListTeamLecturerDto(List<TeamLecturer> entities);
}
