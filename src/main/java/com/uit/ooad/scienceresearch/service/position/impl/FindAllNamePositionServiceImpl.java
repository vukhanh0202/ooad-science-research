package com.uit.ooad.scienceresearch.service.position.impl;

import com.uit.ooad.scienceresearch.dto.level.LevelDto;
import com.uit.ooad.scienceresearch.dto.position.PositionDto;
import com.uit.ooad.scienceresearch.mapper.level.LevelMapper;
import com.uit.ooad.scienceresearch.mapper.position.PositionMapper;
import com.uit.ooad.scienceresearch.repository.LevelRepository;
import com.uit.ooad.scienceresearch.repository.PositionRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.level.IFindAllNameLevelService;
import com.uit.ooad.scienceresearch.service.position.IFindAllNamePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
public class FindAllNamePositionServiceImpl extends AbstractBaseService<Void, List<PositionDto>>
        implements IFindAllNamePositionService<Void, List<PositionDto>> {


    @Autowired
    PositionRepository positionRepository;

    @Autowired
    PositionMapper positionMapper;


    @Override
    public List<PositionDto> doing(Void input) {
        try {
            return positionMapper.toListPositionDto(positionRepository.
                    findAll());
        } catch (Exception e) {
            return null;
        }
    }
}
