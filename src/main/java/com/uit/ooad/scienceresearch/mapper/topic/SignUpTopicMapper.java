package com.uit.ooad.scienceresearch.mapper.topic;

import com.uit.ooad.scienceresearch.constant.EProcess;
import com.uit.ooad.scienceresearch.dto.team.TeamLecturerDto;
import com.uit.ooad.scienceresearch.dto.topic.SignUpTopicDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicFullDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicRegisterDto;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.entity.join.SignUpTopic;
import com.uit.ooad.scienceresearch.entity.join.TeamLecturer;
import com.uit.ooad.scienceresearch.mapper.BaseMapper;
import com.uit.ooad.scienceresearch.mapper.team.converter.TeamLecturerConverter;
import com.uit.ooad.scienceresearch.mapper.topic.converter.SignUpTopicConverter;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/20/2020
 */
@Mapper(componentModel = "spring")
@Component
public abstract class SignUpTopicMapper implements BaseMapper{

    @Autowired
    SignUpTopicConverter signUpTopicConverter;

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
        dto.setDescription(entity.getTopic().getDescription());
        dto.setFacultyName(entity.getTopic().getFaculty().getNameFaculty());
        dto.setFieldTopic(entity.getTopic().getFieldTopic().getFieldName());
        dto.setLevelName(entity.getTopic().getLevel().getNameLevel());
        dto.setNameTopic(entity.getTopic().getNameTopic());
        if (entity.getCompleted().equals(EProcess.APPROVE)){
            dto.setStatus("Completed");
        }else if (entity.getUniversityReview().equals(EProcess.PROCESSING)){
            dto.setStatus("University Review");
        }else if (entity.getFacultyReview().equals(EProcess.PROCESSING)){
            dto.setStatus("Faculty Review");
        }else{
            dto.setStatus("Decline");
        }
    }

    @BeanMapping(qualifiedByName = "toEntity", ignoreByDefault = true)
    @Mapping(source = "start", target = "start")
    @Mapping(source = "facultyReview", target = "facultyReview")
    @Mapping(source = "universityReview", target = "universityReview")
    @Mapping(source = "completed", target = "completed")
    public abstract SignUpTopic toEntity(SignUpTopicDto dto);

    @BeanMapping(qualifiedByName = "toTopicRegister", ignoreByDefault = true)
    public abstract TopicRegisterDto toTopicRegisterDto(SignUpTopic entity);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<TopicRegisterDto> toListTopicRegisterDto(List<SignUpTopic> entities);
}
