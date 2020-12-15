package com.uit.ooad.scienceresearch.service.lecturer.impl;

import com.uit.ooad.scienceresearch.dto.account.AccountLecturerDto;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.exception.InvalidException;
import com.uit.ooad.scienceresearch.mapper.lecturer.LecturerMapper;
import com.uit.ooad.scienceresearch.repository.ContractRepository;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.repository.LecturerRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.lecturer.IRegisterLecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Contract.CONTRACT_NOT_FOUND;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Faculty.FACULTY_NOT_FOUND;

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

        if (accountLecturerDto.getContractId() == null || contractRepository.findById(accountLecturerDto.getContractId()).isEmpty()) {
            throw new InvalidException(messageHelper.getMessage(CONTRACT_NOT_FOUND, accountLecturerDto.getContractId()));
        }
        if (accountLecturerDto.getFacultyId() == null || facultyRepository.findById(accountLecturerDto.getFacultyId()).isEmpty()) {
            throw new InvalidException(messageHelper.getMessage(FACULTY_NOT_FOUND,accountLecturerDto.getFacultyId()));
        }
    }

    @Override
    public Lecturer doing(AccountLecturerDto accountLecturerDto) {
        try {
            return lecturerRepository.save(lecturerMapper.toLecturer(accountLecturerDto));
        } catch (Exception e) {
            return null;
        }
    }
}
