package com.uit.ooad.scienceresearch.service.faculty.impl;

import com.uit.ooad.scienceresearch.dto.contract.ContractDto;
import com.uit.ooad.scienceresearch.dto.faculty.FacultyDto;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.contract.ContractMapper;
import com.uit.ooad.scienceresearch.mapper.faculty.FacultyMapper;
import com.uit.ooad.scienceresearch.repository.ContractRepository;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.contract.IFindContractByIdService;
import com.uit.ooad.scienceresearch.service.faculty.IFindFacultyByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/9/2020
 */
@Service
public class FindFacultyByIdServiceImpl extends AbstractBaseService<Long, FacultyDto>
        implements IFindFacultyByIdService<Long, FacultyDto> {

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    FacultyMapper facultyMapper;

    @Override
    public void preExecute(Long facultyId) {
        if (facultyRepository.findById(facultyId).isEmpty()) {
            throw new NotFoundException("Faculty is not found");
        }
    }

    @Override
    public FacultyDto doing(Long facultyId) {
        return facultyMapper.toFacultyDto(facultyRepository.findById(facultyId).get());
    }
}
