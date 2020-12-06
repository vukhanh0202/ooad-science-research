package com.uit.ooad.scienceresearch.service.account;

import com.uit.ooad.scienceresearch.dto.AccountDto;

import java.util.List;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
public interface IAccountService {

    IFindAccountService<String, AccountDto> getFindAccountService();
}
