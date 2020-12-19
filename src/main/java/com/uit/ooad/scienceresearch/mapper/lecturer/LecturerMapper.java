package com.uit.ooad.scienceresearch.mapper.lecturer;

import com.uit.ooad.scienceresearch.dto.account.AccountLecturerDto;
import com.uit.ooad.scienceresearch.dto.lecturer.LecturerDto;
import com.uit.ooad.scienceresearch.dto.lecturer.LecturerFullDto;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.mapper.BaseMapper;
import com.uit.ooad.scienceresearch.mapper.lecturer.converter.LecturerConverter;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Mapper(componentModel = "spring")
@Component
public abstract class LecturerMapper implements BaseMapper {


    @Autowired
    private LecturerConverter lecturerConverter;

    @Named("toEntity")
    @BeforeMapping
    protected void toEntity(AccountLecturerDto dto, @MappingTarget Lecturer entity) {
        entity.setFaculty(lecturerConverter.getFaculty(dto.getFacultyId()));
        entity.setContract(lecturerConverter.getContract(dto.getContractId()));
        entity.setAccount(lecturerConverter.getAccount(dto.getAccountId()));
    }

    @BeanMapping(qualifiedByName = "toEntity", ignoreByDefault = true)
    @Mapping(source = "lecturerId", target = "lecturerId")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "dob", target = "dob")
    @Mapping(source = "major", target = "major")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "position", target = "position")
    public abstract Lecturer toLecturer(AccountLecturerDto accountLecturerDto);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "lecturerId", target = "lecturerId")
    @Mapping(source = "dob", target = "dob")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "major", target = "major")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "position", target = "position")
    @Mapping(source = "contract.nameContract", target = "contract")
    @Mapping(source = "faculty.nameFaculty", target = "faculty")
    @Mapping(source = "deleted", target = "isDeleted")
    @Mapping(source = "createdAt", target = "createdAt")
    //@Mapping(source = "updatedAt", target = "updatedAt")
    //@Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "getAudit")
    //@Mapping(source = "updatedBy", target = "updatedBy", qualifiedByName = "getAudit")
    public abstract LecturerFullDto toLecturerFullDto(Lecturer entity);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<LecturerFullDto> toListLecturerFullDto(List<Lecturer> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateLecturer(LecturerDto lecturerDto, @MappingTarget Lecturer entity);
}
