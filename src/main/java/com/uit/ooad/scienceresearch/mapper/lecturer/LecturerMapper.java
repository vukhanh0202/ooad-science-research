package com.uit.ooad.scienceresearch.mapper.lecturer;

import com.uit.ooad.scienceresearch.dto.account.AccountLecturerDto;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.mapper.lecturer.converter.LecturerConverter;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Mapper(componentModel = "spring")
@Component
public abstract class LecturerMapper {


    @Autowired
    private LecturerConverter lecturerConverter;

    @Named("toEntity")
    @BeforeMapping
    protected void toEntity(AccountLecturerDto dto, @MappingTarget Lecturer entity) {
        entity.setFaculty(lecturerConverter.getFaculty(dto.getFaculty_id()));
        entity.setContract(lecturerConverter.getContract(dto.getContract_id()));
        entity.setAccount(lecturerConverter.getAccount(dto.getAccount_id()));
    }

    @BeanMapping(qualifiedByName = "toEntity",ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "dob", target = "dob")
    @Mapping(source = "major", target = "major")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    public abstract Lecturer toLecturer(AccountLecturerDto accountLecturerDto);
}
