package com.uit.ooad.scienceresearch.mapper.account;

import com.uit.ooad.scienceresearch.dto.account.AccountDto;
import com.uit.ooad.scienceresearch.dto.account.AccountLecturerDto;
import com.uit.ooad.scienceresearch.dto.account.AccountLecturerSearchDto;
import com.uit.ooad.scienceresearch.dto.topic.TopicDto;
import com.uit.ooad.scienceresearch.entity.Account;
import com.uit.ooad.scienceresearch.entity.Topic;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Mapper(componentModel = "spring")
@Component
public abstract class AccountMapper {

    @Named("toAccountSearchDto")
    @BeforeMapping
    protected void toAccountSearchDto(Account entity, @MappingTarget AccountLecturerSearchDto dto) {
        dto.setLecturerId(entity.getLecturers().get(0).getLecturerId());
    }

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    public abstract AccountDto toAccountDto(Account entity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "accountId", target = "accountId")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "role", target = "role.code")
    public abstract Account toAccount(AccountLecturerDto accountLecturerDto);

    @BeanMapping(qualifiedByName = "toAccountSearchDto", ignoreByDefault = true)
    @Mapping(source = "username", target = "username")
    public abstract AccountLecturerSearchDto toAccountLecturerSearchDto(Account entity);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<AccountLecturerSearchDto> toListAccountLecturerSearchDto(List<Account> entities);


}
