package com.uit.ooad.scienceresearch.service.lecturer.impl;

import com.uit.ooad.scienceresearch.dto.lecturer.LecturerFullDto;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.lecturer.LecturerMapper;
import com.uit.ooad.scienceresearch.repository.ContractRepository;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.repository.LecturerRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.lecturer.IFindAllLecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Contract.CONTRACT_NOT_FOUND;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Faculty.FACULTY_NOT_FOUND;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class FindAllLecturerServiceImpl extends AbstractBaseService<IFindAllLecturerService.Input, List<LecturerFullDto>>
        implements IFindAllLecturerService<IFindAllLecturerService.Input, List<LecturerFullDto>> {


    @Autowired
    LecturerRepository lecturerRepository;

    @Autowired
    LecturerMapper lecturerMapper;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    ContractRepository contractRepository;


    @Override
    public void preExecute(Input input) {
        if (input.getFacultyId() != null) {
            if (facultyRepository.findById(input.getFacultyId()).isEmpty()) {
                throw new NotFoundException(messageHelper.getMessage(FACULTY_NOT_FOUND, input.getFacultyId()));
            }
        }
        if (input.getContractId() != null) {
            if (contractRepository.findById(input.getContractId()).isEmpty()) {
                throw new NotFoundException(messageHelper.getMessage(CONTRACT_NOT_FOUND, input.getContractId()));
            }
        }
    }

    @Override
    public List<LecturerFullDto> doing(IFindAllLecturerService.Input input) {
        try {
            return lecturerMapper.toListLecturerFullDto(lecturerRepository.
                    findCustomerByFullNameContainingAndContractIdAndFacultyId(input.getSearch(),input.getContractId(),
                            input.getFacultyId(),input.createPageable(Sort.Direction.ASC, "createdAt")));
        } catch (Exception e) {
            return null;
        }
    }
}
