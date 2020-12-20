package com.uit.ooad.scienceresearch.mapper.topic;

import com.uit.ooad.scienceresearch.constant.EProcess;
import com.uit.ooad.scienceresearch.dto.team.TeamLecturerDto;
import com.uit.ooad.scienceresearch.dto.topic.SignUpTopicDto;
import com.uit.ooad.scienceresearch.dto.topic.SignUpTopicFullDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicFullDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicRegisterDto;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.entity.join.TeamLecturer;
import com.uit.ooad.scienceresearch.mapper.BaseMapper;
import com.uit.ooad.scienceresearch.mapper.lecturer.LecturerMapper;
import com.uit.ooad.scienceresearch.mapper.team.TeamLecturerMapper;
import com.uit.ooad.scienceresearch.mapper.team.converter.TeamLecturerConverter;
import com.uit.ooad.scienceresearch.mapper.topic.converter.SignUpTopicConverter;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/20/2020
 */
@Mapper(componentModel = "spring")
@Component
public abstract class SignUpTopicMapper implements BaseMapper {

    @Autowired
    SignUpTopicConverter signUpTopicConverter;

    @Autowired
    TeamLecturerMapper teamLecturerMapper;

    @Autowired
    TopicMapper topicMapper;

    @Named("toEntity")
    @BeforeMapping
    protected void toEntity(SignUpTopicDto dto, @MappingTarget SignUpTopic entity) {
        entity.setTopic(signUpTopicConverter.getTopic(dto.getTopicId()));
        entity.setTeam(signUpTopicConverter.getTeam(dto.getTeamId()));
    }

    @Named("toTopicRegister")
    @BeforeMapping
    protected void toTopicRegister(SignUpTopic entity, @MappingTarget TopicRegisterDto dto) {
        dto.setTopicId(entity.getTopic().getTopicId());
        dto.setTeamId(entity.getTeam().getTeamId());
        dto.setDescription(entity.getTopic().getDescription());
        dto.setFacultyName(entity.getTopic().getFaculty().getNameFaculty());
        dto.setFieldTopic(entity.getTopic().getFieldTopic().getFieldName());
        dto.setLevelName(entity.getTopic().getLevel().getNameLevel());
        dto.setNameTopic(entity.getTopic().getNameTopic());
        if (entity.getCompleted().equals(EProcess.APPROVE)) {
            dto.setStatus("Completed");
        } else if (entity.getUniversityReview().equals(EProcess.PROCESSING)) {
            dto.setStatus("University Review");
        } else if (entity.getFacultyReview().equals(EProcess.PROCESSING)) {
            dto.setStatus("Faculty Review");
        } else {
            dto.setStatus("Decline");
        }
    }

    @Named("toFullTopicRegister")
    @BeforeMapping
    protected void toFullTopicRegister(SignUpTopic entity, @MappingTarget SignUpTopicFullDto dto) {
        dto.setStart(entity.getStart());
        dto.setFacultyReview(entity.getFacultyReview());
        if (!entity.getUniversityReview().equals(EProcess.NONE)) {
            dto.setUniversityReview(entity.getUniversityReview());
        }
        dto.setCompleted(entity.getCompleted());
        dto.setTeam(teamLecturerMapper.toListTeamLecturerDto(entity
                .getTeam()
                .getGroupLecturers()));
        dto.setTopic(topicMapper.toTopicFullDto(entity.getTopic()));
    }

    @BeanMapping(qualifiedByName = "toEntity", ignoreByDefault = true)
    @Mapping(source = "start", target = "start")
    @Mapping(source = "facultyReview", target = "facultyReview")
    @Mapping(source = "universityReview", target = "universityReview")
    @Mapping(source = "completed", target = "completed")
    public abstract SignUpTopic toEntity(SignUpTopicDto dto);

    @BeanMapping(qualifiedByName = "toTopicRegister", ignoreByDefault = true)
    public abstract TopicRegisterDto toTopicRegisterDto(SignUpTopic entity);

    @BeanMapping(qualifiedByName = "toFullTopicRegister", ignoreByDefault = true)
    public abstract SignUpTopicFullDto toSignUpTopicFullDto(SignUpTopic entity);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<TopicRegisterDto> toListTopicRegisterDto(List<SignUpTopic> entities);
}
