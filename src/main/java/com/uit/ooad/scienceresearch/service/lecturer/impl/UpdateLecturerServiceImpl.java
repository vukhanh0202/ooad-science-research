package com.uit.ooad.scienceresearch.service.lecturer.impl;

import com.uit.ooad.scienceresearch.dto.lecturer.LecturerDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicDto;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.lecturer.LecturerMapper;
import com.uit.ooad.scienceresearch.mapper.topic.TopicMapper;
import com.uit.ooad.scienceresearch.repository.*;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.lecturer.IUpdateLecturerService;
import com.uit.ooad.scienceresearch.service.topic.IUpdateTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (lecturerRepository.findById(lecturerDto.getId()).isEmpty()){
            throw new NotFoundException("Lecturer Not Found!");
        }
        if (facultyRepository.findById(lecturerDto.getFacultyId()).isEmpty()){
            throw new NotFoundException("Faculty Not Found!");
        }
        if (contractRepository.findById(lecturerDto.getContractId()).isEmpty()){
            throw new NotFoundException("Contract Not Found!");
        }
    }

    @Override
    public Boolean doing(LecturerDto lecturerDto) {
        try{
            Lecturer oldLecturer = lecturerRepository.findById(lecturerDto.getId()).get();
            lecturerMapper.updateLecturer(lecturerDto, oldLecturer);
            lecturerRepository.save(oldLecturer);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
