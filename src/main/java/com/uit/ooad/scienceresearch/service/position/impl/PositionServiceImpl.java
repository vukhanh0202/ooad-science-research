package com.uit.ooad.scienceresearch.service.position.impl;

import com.uit.ooad.scienceresearch.service.level.IFindAllNameLevelService;
import com.uit.ooad.scienceresearch.service.level.ILevelService;
import com.uit.ooad.scienceresearch.service.position.IFindAllNamePositionService;
import com.uit.ooad.scienceresearch.service.position.IPositionService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Service
@Getter
public class PositionServiceImpl implements IPositionService {

    @Autowired
    IFindAllNamePositionService findAllNamePositionService;
}
