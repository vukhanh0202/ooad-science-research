package com.uit.ooad.scienceresearch.service.field;

import com.uit.ooad.scienceresearch.dto.field.FieldTopicDto;
import com.uit.ooad.scienceresearch.dto.level.LevelDto;
import com.uit.ooad.scienceresearch.service.level.IFindAllNameLevelService;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
public interface IFieldService {

    IFindAllNameFieldService<Void, List<FieldTopicDto>> getFindAllNameFieldService();
}
