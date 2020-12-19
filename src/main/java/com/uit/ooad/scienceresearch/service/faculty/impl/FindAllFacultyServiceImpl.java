package com.uit.ooad.scienceresearch.service.faculty.impl;

import com.uit.ooad.scienceresearch.dto.faculty.FacultyFullDto;
import com.uit.ooad.scienceresearch.mapper.faculty.FacultyMapper;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.faculty.IFindAllFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class FindAllFacultyServiceImpl extends AbstractBaseService<IFindAllFacultyService.Input, List<FacultyFullDto>>
        implements IFindAllFacultyService<IFindAllFacultyService.Input, List<FacultyFullDto>> {


    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    FacultyMapper facultyMapper;


    @Override
    public List<FacultyFullDto> doing(Input input) {
        try {
            return facultyMapper.toFacultyFullListDto(facultyRepository.
                    findAllByNameFacultyContaining(input.getSearch(), input.createPageable(Sort.Direction.ASC,
                            "nameFaculty")));
        } catch (Exception e) {
            return null;
        }
    }
}