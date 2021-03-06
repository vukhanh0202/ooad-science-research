package com.uit.ooad.scienceresearch.service.faculty.impl;

import com.uit.ooad.scienceresearch.dto.faculty.FacultyFullDto;
import com.uit.ooad.scienceresearch.entity.Faculty;
import com.uit.ooad.scienceresearch.exception.InvalidException;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.faculty.FacultyMapper;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.faculty.IUpdateFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Faculty.FACULTY_EXIST;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Faculty.FACULTY_NOT_FOUND;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class UpdateFacultyServiceImpl extends AbstractBaseService<FacultyFullDto, Boolean>
        implements IUpdateFacultyService<FacultyFullDto, Boolean> {

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    FacultyMapper facultyMapper;

    @Override
    public void preExecute(FacultyFullDto facultyDto) {
        if (facultyRepository.findById(facultyDto.getFacultyId()).isEmpty()) {
            throw new NotFoundException(messageHelper.getMessage(FACULTY_NOT_FOUND, facultyDto.getFacultyId()));
        }
        if (facultyRepository.findByNameFaculty(facultyDto.getNameFaculty()).isPresent()) {
            throw new InvalidException(messageHelper.getMessage(FACULTY_EXIST, facultyDto.getNameFaculty()));
        }
    }
    @Override
    public Boolean doing(FacultyFullDto facultyDto) {
        try {
            Faculty oldFaculty = facultyRepository.findById(facultyDto.getFacultyId()).get();
            facultyMapper.updateFaculty(facultyDto, oldFaculty);
            facultyRepository.save(oldFaculty);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
