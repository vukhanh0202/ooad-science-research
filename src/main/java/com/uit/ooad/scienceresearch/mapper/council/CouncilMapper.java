package com.uit.ooad.scienceresearch.mapper.council;

import com.uit.ooad.scienceresearch.dto.council.CouncilLecturerDto;
import com.uit.ooad.scienceresearch.dto.council.RecordDto;
import com.uit.ooad.scienceresearch.dto.council.TopicReview;
import com.uit.ooad.scienceresearch.dto.team.TeamLecturerDto;
import com.uit.ooad.scienceresearch.entity.join.CouncilLecturer;
import com.uit.ooad.scienceresearch.entity.join.Record;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.entity.join.TeamLecturer;
import com.uit.ooad.scienceresearch.mapper.BaseMapper;
import com.uit.ooad.scienceresearch.mapper.council.converter.CouncilConverter;
import com.uit.ooad.scienceresearch.mapper.team.TeamLecturerMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/8/2020
 */
@Mapper(componentModel = "spring")
@Component
public abstract class CouncilMapper implements BaseMapper {

    @Autowired
    CouncilConverter councilConverter;

    @Autowired
    TeamLecturerMapper teamLecturerMapper;

    @Named("toEntity")
    @BeforeMapping
    protected void toEntity(CouncilLecturerDto dto, @MappingTarget CouncilLecturer entity) {
        entity.setCouncil(councilConverter.getCouncil(dto.getCouncilId()));
        entity.setLecturer(councilConverter.getLecturer(dto.getLecturerId()));
        entity.setPosition(councilConverter.getPosition(dto.getPositionId()));
    }

    @Named("toRecordEntity")
    @BeforeMapping
    protected void toRecordEntity(RecordDto dto, @MappingTarget Record entity) {
        entity.setCouncil(councilConverter.getCouncil(dto.getCouncilId()));
        entity.setLecturer(councilConverter.getLecturer(dto.getLecturerId()));
        entity.setTeam(councilConverter.getTeam(dto.getTeamId()));
        entity.setTopic(councilConverter.getTopic(dto.getTopicId()));
    }

    @Named("toTopicReview")
    @BeforeMapping
    protected void toTopicReview(SignUpTopic entity, @MappingTarget TopicReview dto) {
        dto.setTopicId(entity.getTopic().getTopicId());
        dto.setTeamId(entity.getTeam().getTeamId());
        dto.setId(dto.getTopicId() + dto.getTeamId());
        dto.setFacultyName(entity.getTopic().getFaculty().getNameFaculty());
        dto.setFieldTopic(entity.getTopic().getFieldTopic().getFieldName());
        dto.setLevelName(entity.getTopic().getLevel().getNameLevel());
        dto.setNameTopic(entity.getTopic().getNameTopic());
        dto.setYear(entity.getTopic().getYear());
        dto.setStatus(entity.getFinish() ? "COMPLETED" : "NOT COMPLETED");
        dto.setMembers(teamLecturerMapper.toListTeamLecturerDto(entity.getTeam().getGroupLecturers()));
        List<TeamLecturer> arr = entity.getTeam().getGroupLecturers();

        for (TeamLecturer item : arr) {
            if (item.isPrimary()) {
                dto.setLeader(item.getLecturer().getFullName());
            }
        }
    }

    @BeanMapping(qualifiedByName = "toTopicReview", ignoreByDefault = true)
    public abstract TopicReview toTopicReview(SignUpTopic entity);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<TopicReview> toListTopicReview(List<SignUpTopic> entities);

    @BeanMapping(qualifiedByName = "toEntity", ignoreByDefault = true)
    public abstract CouncilLecturer toEntity(CouncilLecturerDto dto);

    @BeanMapping(qualifiedByName = "toRecordEntity", ignoreByDefault = true)
    @Mapping(source = "score", target = "score")
    public abstract Record toRecordEntity(RecordDto dto);
}
