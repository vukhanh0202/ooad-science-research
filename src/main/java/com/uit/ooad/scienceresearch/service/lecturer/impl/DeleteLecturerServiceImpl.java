package com.uit.ooad.scienceresearch.service.lecturer.impl;

import com.uit.ooad.scienceresearch.dto.lecturer.LecturerDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicDto;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.entity.Topic;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.repository.LecturerRepository;
import com.uit.ooad.scienceresearch.repository.TopicRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.lecturer.IDeleteLecturerService;
import com.uit.ooad.scienceresearch.service.topic.IDeleteTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/8/2020
 */
@Service
public class DeleteLecturerServiceImpl extends AbstractBaseService<LecturerDto, Boolean>
        implements IDeleteLecturerService<LecturerDto, Boolean> {

    @Autowired
    LecturerRepository lecturerRepository;

    @Override
    public void preExecute(LecturerDto lecturerDto) {
        if (lecturerRepository.findById(lecturerDto.getId()).isEmpty()){
            throw new NotFoundException("Lecturer Not Found!");
        }
    }

    @Override
    public Boolean doing(LecturerDto lecturerDto) {
        try{
            Lecturer lecturer = lecturerRepository.findById(lecturerDto.getId()).get();
            lecturerRepository.delete(lecturer);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
