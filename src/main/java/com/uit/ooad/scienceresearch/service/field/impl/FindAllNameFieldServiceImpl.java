package com.uit.ooad.scienceresearch.service.field.impl;

import com.uit.ooad.scienceresearch.dto.field.FieldTopicDto;
import com.uit.ooad.scienceresearch.dto.level.LevelDto;
import com.uit.ooad.scienceresearch.mapper.field.FieldTopicMapper;
import com.uit.ooad.scienceresearch.mapper.level.LevelMapper;
import com.uit.ooad.scienceresearch.repository.FieldTopicRepository;
import com.uit.ooad.scienceresearch.repository.LevelRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.field.IFindAllNameFieldService;
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
public class FindAllNameFieldServiceImpl extends AbstractBaseService<Void, List<FieldTopicDto>>
        implements IFindAllNameFieldService<Void, List<FieldTopicDto>> {


    @Autowired
    FieldTopicRepository fieldTopicRepository;

    @Autowired
    FieldTopicMapper fieldTopicMapper;


    @Override
    public List<FieldTopicDto> doing(Void input) {
        try {
            return fieldTopicMapper.toListFieldTopicDto(fieldTopicRepository.
                    findAll());
        } catch (Exception e) {
            return null;
        }
    }
}
