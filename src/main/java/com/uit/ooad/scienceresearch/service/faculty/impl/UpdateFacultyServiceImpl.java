package com.uit.ooad.scienceresearch.service.faculty.impl;

import com.uit.ooad.scienceresearch.dto.contract.ContractDto;
import com.uit.ooad.scienceresearch.dto.faculty.FacultyDto;
import com.uit.ooad.scienceresearch.entity.Contract;
import com.uit.ooad.scienceresearch.entity.Faculty;
import com.uit.ooad.scienceresearch.exception.InvalidException;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.contract.ContractMapper;
import com.uit.ooad.scienceresearch.mapper.faculty.FacultyMapper;
import com.uit.ooad.scienceresearch.repository.ContractRepository;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.contract.IUpdateContractService;
import com.uit.ooad.scienceresearch.service.faculty.IUpdateFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class UpdateFacultyServiceImpl extends AbstractBaseService<FacultyDto, Boolean>
        implements IUpdateFacultyService<FacultyDto, Boolean> {

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    FacultyMapper facultyMapper;

    @Override
    public void preExecute(FacultyDto facultyDto) {
        if (facultyRepository.findById(facultyDto.getId()).isEmpty()) {
            throw new NotFoundException("Faculty is  Not Found!");
        }
        if (facultyRepository.findByNameFaculty(facultyDto.getNameFaculty()).isPresent()) {
            throw new InvalidException("Faculty is Exist!");
        }
    }

    @Override
    public Boolean doing(FacultyDto facultyDto) {
        try {
            Faculty oldFaculty = facultyRepository.findById(facultyDto.getId()).get();
            facultyMapper.updateFaculty(facultyDto, oldFaculty);
            facultyRepository.save(oldFaculty);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
