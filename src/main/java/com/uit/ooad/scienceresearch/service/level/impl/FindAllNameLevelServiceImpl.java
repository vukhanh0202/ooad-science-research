package com.uit.ooad.scienceresearch.service.level.impl;

import com.uit.ooad.scienceresearch.dto.level.LevelDto;
import com.uit.ooad.scienceresearch.mapper.level.LevelMapper;
import com.uit.ooad.scienceresearch.repository.LevelRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.faculty.IFindAllNameFacultyService;
import com.uit.ooad.scienceresearch.service.level.IFindAllNameLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class FindAllNameLevelServiceImpl extends AbstractBaseService<Void, List<LevelDto>>
        implements IFindAllNameLevelService<Void, List<LevelDto>> {


    @Autowired
    LevelRepository levelRepository;

    @Autowired
    LevelMapper levelMapper;


    @Override
    public List<LevelDto> doing(Void input) {
        try {
            return levelMapper.toListLevelDto(levelRepository.
                    findAll());
        } catch (Exception e) {
            return null;
        }
    }
}
