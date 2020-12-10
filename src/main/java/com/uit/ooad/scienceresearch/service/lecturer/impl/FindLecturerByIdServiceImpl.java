package com.uit.ooad.scienceresearch.service.lecturer.impl;

import com.uit.ooad.scienceresearch.dto.lecturer.LecturerFullDto;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.lecturer.LecturerMapper;
import com.uit.ooad.scienceresearch.repository.LecturerRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.lecturer.IFindLecturerByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/9/2020
 */
@Service
public class FindLecturerByIdServiceImpl extends AbstractBaseService<Long, LecturerFullDto>
        implements IFindLecturerByIdService<Long, LecturerFullDto> {

    @Autowired
    LecturerRepository lecturerRepository;

    @Autowired
    LecturerMapper lecturerMapper;

    @Override
    public void preExecute(Long lecturerId) {
        if (lecturerRepository.findById(lecturerId).isEmpty()) {
            throw new NotFoundException("Lecturer is not found");
        }
    }

    @Override
    public LecturerFullDto doing(Long lecturerId) {
        return lecturerMapper.toLecturerFullDto(lecturerRepository.findById(lecturerId).get());
    }
}
