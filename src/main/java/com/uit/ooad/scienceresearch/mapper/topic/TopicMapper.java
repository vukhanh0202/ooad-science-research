package com.uit.ooad.scienceresearch.mapper.topic;

import com.uit.ooad.scienceresearch.dto.topic.TopicDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicFullDto;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.mapper.BaseMapper;
import com.uit.ooad.scienceresearch.mapper.topic.converter.TopicConverter;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Mapper(componentModel = "spring")
@Component
public abstract class TopicMapper implements BaseMapper {

    @Autowired
    private TopicConverter topicConverter;

    @Named("toEntity")
    @BeforeMapping
    protected void toEntity(TopicDto dto, @MappingTarget Topic entity) {
        entity.setFaculty(topicConverter.getFaculty(dto.getFaculty_id()));
        entity.setLevel(topicConverter.getLevel(dto.getLevel_id()));
        entity.setFieldTopic(topicConverter.getFieldTopic(dto.getField_id()));
    }


    @BeanMapping(qualifiedByName = "toEntity",ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nameTopic", target = "nameTopic")
    @Mapping(source = "year", target = "year")
    public abstract Topic toTopic(TopicDto topicDto);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nameTopic", target = "nameTopic")
    @Mapping(source = "faculty", target = "faculty")
    @Mapping(source = "level", target = "level")
    @Mapping(source = "fieldTopic", target = "fieldTopic")
    @Mapping(source = "deleted", target = "isDeleted")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "getAudit")
    @Mapping(source = "updatedBy", target = "updatedBy", qualifiedByName = "getAudit")
    public abstract TopicFullDto toTopicFullDto(Topic entity);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<TopicFullDto> toListTopicFullDto(List<Topic> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateTopic(TopicDto topicDto, @MappingTarget Topic entity);
}
