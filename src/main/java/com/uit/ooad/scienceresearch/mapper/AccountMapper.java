package com.uit.ooad.scienceresearch.mapper;

import com.uit.ooad.scienceresearch.dto.AccountDto;
import com.uit.ooad.scienceresearch.entity.Account;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Mapper(componentModel = "spring")
@Component
public abstract class AccountMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    public abstract AccountDto toAccountDto(Account entity);
}
