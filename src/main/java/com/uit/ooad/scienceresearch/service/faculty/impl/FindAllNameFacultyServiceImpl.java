package com.uit.ooad.scienceresearch.service.faculty.impl;

import com.uit.ooad.scienceresearch.dto.faculty.FacultyDto;
import com.uit.ooad.scienceresearch.mapper.faculty.FacultyMapper;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.faculty.IFindAllNameFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class FindAllNameFacultyServiceImpl extends AbstractBaseService<Void, List<FacultyDto>>
        implements IFindAllNameFacultyService<Void, List<FacultyDto>> {


    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    FacultyMapper facultyMapper;


    @Override
    public List<FacultyDto> doing(Void input) {
        try {
            return facultyMapper.toaFacultyListDto(facultyRepository.
                    findAll());
        } catch (Exception e) {
            return null;
        }
    }
}
