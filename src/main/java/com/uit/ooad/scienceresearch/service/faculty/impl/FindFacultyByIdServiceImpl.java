package com.uit.ooad.scienceresearch.service.faculty.impl;

import com.uit.ooad.scienceresearch.dto.faculty.FacultyFullDto;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.faculty.FacultyMapper;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.faculty.IFindFacultyByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Faculty.FACULTY_NOT_FOUND;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/9/2020
 */
@Service
public class FindFacultyByIdServiceImpl extends AbstractBaseService<Long, FacultyFullDto>
        implements IFindFacultyByIdService<Long, FacultyFullDto> {

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    FacultyMapper facultyMapper;

    @Override
    public void preExecute(Long facultyId) {
        if (facultyRepository.findById(facultyId).isEmpty()) {
            throw new NotFoundException(messageHelper.getMessage(FACULTY_NOT_FOUND, facultyId));
        }
    }

    @Override
    public FacultyFullDto doing(Long facultyId) {
        return facultyMapper.toFacultyFullDto(facultyRepository.findById(facultyId).get());
    }
}
