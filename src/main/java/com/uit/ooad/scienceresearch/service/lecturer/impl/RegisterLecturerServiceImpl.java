package com.uit.ooad.scienceresearch.service.lecturer.impl;

import com.uit.ooad.scienceresearch.dto.account.AccountLecturerDto;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.exception.InvalidException;
import com.uit.ooad.scienceresearch.mapper.LecturerMapper;
import com.uit.ooad.scienceresearch.repository.ContractRepository;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.repository.LecturerRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.lecturer.IRegisterLecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Service
public class RegisterLecturerServiceImpl extends AbstractBaseService<AccountLecturerDto, Lecturer>
        implements IRegisterLecturerService<AccountLecturerDto, Lecturer> {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    LecturerRepository lecturerRepository;

    @Autowired
    LecturerMapper lecturerMapper;

    @Override
    public void preExecute(AccountLecturerDto accountLecturerDto) {
        if (accountLecturerDto.getFullName() == null) {
            throw new InvalidException("Invalid full name");
        }
        if (accountLecturerDto.getContract_id() == null || contractRepository.findById(accountLecturerDto.getContract_id()).isEmpty()) {
            throw new InvalidException("Invalid contract");
        }
        if (accountLecturerDto.getFaculty_id() == null || facultyRepository.findById(accountLecturerDto.getFaculty_id()).isEmpty()) {
            throw new InvalidException("Invalid faculty");
        }
    }

    @Override
    public Lecturer doing(AccountLecturerDto accountLecturerDto) {
        try{
            return lecturerRepository.save(lecturerMapper.toLecturer(accountLecturerDto));
        }catch (Exception e){
            return null;
        }
    }
}
