package com.uit.ooad.scienceresearch.mapper.field;

import com.uit.ooad.scienceresearch.dto.field.FieldTopicDto;
import com.uit.ooad.scienceresearch.entity.FieldTopic;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/8/2020
 */
@Mapper(componentModel = "spring")
@Component
public abstract class FieldTopicMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "fieldName", target = "fieldName")
    public abstract FieldTopicDto toFieldTopicDto(FieldTopic entity);
}
