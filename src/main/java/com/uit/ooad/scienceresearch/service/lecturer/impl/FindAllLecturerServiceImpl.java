package com.uit.ooad.scienceresearch.service.lecturer.impl;

import com.uit.ooad.scienceresearch.dto.lecturer.LecturerFullDto;
import com.uit.ooad.scienceresearch.mapper.lecturer.LecturerMapper;
import com.uit.ooad.scienceresearch.repository.LecturerRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.lecturer.IFindAllLecturerService;
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
public class FindAllLecturerServiceImpl extends AbstractBaseService<IFindAllLecturerService.Input, List<LecturerFullDto>>
        implements IFindAllLecturerService<IFindAllLecturerService.Input, List<LecturerFullDto>> {


    @Autowired
    LecturerRepository lecturerRepository;

    @Autowired
    LecturerMapper lecturerMapper;


    @Override
    public List<LecturerFullDto> doing(IFindAllLecturerService.Input input) {
        try {
            if (input.getSearch().equals("")) {
                return lecturerMapper.toListLecturerFullDto(lecturerRepository.
                        findAll(input.createPageable(Sort.Direction.ASC, "id")).getContent());
            } else {
                return lecturerMapper.toListLecturerFullDto(lecturerRepository.
                        findAllByFullNameContaining(input.getSearch(), input.createPageable(Sort.Direction.ASC, "id")));
            }

        } catch (Exception e) {
            return null;
        }
    }
}
