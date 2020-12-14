package com.uit.ooad.scienceresearch.mapper.contract;

import com.uit.ooad.scienceresearch.dto.contract.ContractDto;
import com.uit.ooad.scienceresearch.entity.Contract;
import com.uit.ooad.scienceresearch.mapper.BaseMapper;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/8/2020
 */
@Mapper(componentModel = "spring")
@Component
public abstract class ContractMapper implements BaseMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "contractId", target = "contractId")
    @Mapping(source = "nameContract", target = "nameContract")
    @Mapping(source = "deleted", target = "isDeleted")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "getAudit")
    @Mapping(source = "updatedBy", target = "updatedBy", qualifiedByName = "getAudit")
    public abstract ContractDto toContractDto(Contract entity);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<ContractDto> toContractListDto(List<Contract> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "nameContract", target = "nameContract")
    @Mapping(source = "isDeleted", target = "deleted")
    public abstract void updateContract(ContractDto contractDto, @MappingTarget Contract entity);

    @Mapping(source = "nameContract", target = "nameContract")
    public abstract Contract toContract(ContractDto contractDto);
}
