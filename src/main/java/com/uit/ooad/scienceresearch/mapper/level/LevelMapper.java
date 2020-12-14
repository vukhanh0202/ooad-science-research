package com.uit.ooad.scienceresearch.mapper.level;

import com.uit.ooad.scienceresearch.dto.level.LevelDto;
import com.uit.ooad.scienceresearch.entity.Level;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/8/2020
 */
@Mapper(componentModel = "spring")
@Component
public abstract class LevelMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "levelId", target = "levelId")
    @Mapping(source = "nameLevel", target = "nameLevel")
    public abstract LevelDto toLevelDto(Level entity);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<LevelDto> toListLevelDto(List<Level> entities);
}
