package com.uit.ooad.scienceresearch.mapper.position;

import com.uit.ooad.scienceresearch.dto.level.LevelDto;
import com.uit.ooad.scienceresearch.dto.position.PositionDto;
import com.uit.ooad.scienceresearch.entity.Level;
import com.uit.ooad.scienceresearch.entity.Position;
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
public abstract class PositionMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "positionId", target = "positionId")
    @Mapping(source = "namePosition", target = "namePosition")
    public abstract PositionDto toPositionDto(Position entity);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<PositionDto> toListPositionDto(List<Position> entities);
}
