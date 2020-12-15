package com.uit.ooad.scienceresearch.service.lecturer.impl;

import com.uit.ooad.scienceresearch.dto.lecturer.LecturerDto;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.lecturer.LecturerMapper;
import com.uit.ooad.scienceresearch.repository.*;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.lecturer.IUpdateLecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.uit.ooad.scienceresearch.constant.MessageCode.Contract.CONTRACT_NOT_FOUND;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Faculty.FACULTY_NOT_FOUND;
import static com.uit.ooad.scienceresearch.constant.MessageCode.Lecturer.LECTURER_NOT_FOUND;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class UpdateLecturerServiceImpl extends AbstractBaseService<LecturerDto, Boolean>
        implements IUpdateLecturerService<LecturerDto, Boolean> {

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    LecturerRepository lecturerRepository;

    @Autowired
    LecturerMapper lecturerMapper;


    @Override
    public void preExecute(LecturerDto lecturerDto) {
        if (lecturerRepository.findById(lecturerDto.getLecturerId()).isEmpty()){
            throw new NotFoundException(messageHelper.getMessage(LECTURER_NOT_FOUND,lecturerDto.getFullName()));
        }
        if (facultyRepository.findById(lecturerDto.getFacultyId()).isEmpty()){
            throw new NotFoundException(messageHelper.getMessage(FACULTY_NOT_FOUND,lecturerDto.getFacultyId()));
        }
        if (contractRepository.findById(lecturerDto.getContractId()).isEmpty()){
            throw new NotFoundException(messageHelper.getMessage(CONTRACT_NOT_FOUND,lecturerDto.getContractId()));
        }
    }

    @Override
    public Boolean doing(LecturerDto lecturerDto) {
        try{
            Lecturer oldLecturer = lecturerRepository.findById(lecturerDto.getLecturerId()).get();
            lecturerMapper.updateLecturer(lecturerDto, oldLecturer);
            lecturerRepository.save(oldLecturer);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
