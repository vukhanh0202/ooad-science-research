package com.uit.ooad.scienceresearch.mapper.faculty;

import com.uit.ooad.scienceresearch.dto.faculty.FacultyDto;
import com.uit.ooad.scienceresearch.dto.faculty.FacultyFullDto;
import com.uit.ooad.scienceresearch.entity.Faculty;
import com.uit.ooad.scienceresearch.mapper.BaseMapper;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/8/2020
 */
@Mapper(componentModel = "spring")
@Component
public abstract class FacultyMapper implements BaseMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "facultyId", target = "facultyId")
    @Mapping(source = "nameFaculty", target = "nameFaculty")
    @Mapping(source = "nameUniversity", target = "nameUniversity")
    @Mapping(source = "deleted", target = "isDeleted")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "getAudit")
    @Mapping(source = "updatedBy", target = "updatedBy", qualifiedByName = "getAudit")
    public abstract FacultyFullDto toFacultyFullDto(Faculty entity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "facultyId", target = "facultyId")
    @Mapping(source = "nameFaculty", target = "nameFaculty")
    public abstract FacultyDto toFacultyDto(Faculty entity);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<FacultyDto> toaFacultyListDto(List<Faculty> entities);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<FacultyFullDto> toFacultyFullListDto(List<Faculty> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "facultyId", target = "facultyId")
    @Mapping(source = "nameFaculty", target = "nameFaculty")
    @Mapping(source = "nameUniversity", target = "nameUniversity")
    public abstract void updateFaculty(FacultyFullDto facultyDto, @MappingTarget Faculty entity);

    @Mapping(source = "nameFaculty", target = "nameFaculty")
    @Mapping(source = "nameUniversity", target = "nameUniversity")
    public abstract Faculty toFaculty(FacultyFullDto facultyDto);
}
