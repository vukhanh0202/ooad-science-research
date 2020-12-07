package com.uit.ooad.scienceresearch.mapper.faculty;

import com.uit.ooad.scienceresearch.dto.faculty.FacultyDto;
import com.uit.ooad.scienceresearch.entity.Faculty;
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
public abstract class FacultyMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nameFaculty", target = "nameFaculty")
    @Mapping(source = "nameUniversity", target = "nameUniversity")
    public abstract FacultyDto toFacultyDto(Faculty entity);
}
