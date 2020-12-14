package com.uit.ooad.scienceresearch.mapper;

import com.uit.ooad.scienceresearch.dto.account.AccountDto;
import com.uit.ooad.scienceresearch.entity.Account;
import org.mapstruct.Named;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/9/2020
 */
public interface BaseMapper {

    @Named("getAudit")
    default AccountDto getAudit(Account account) {
        if (account == null) return null;
        return new AccountDto(account.getAccountId(), account.getUsername(), account.getLecturers().get(0).getFullName());
    }
}
