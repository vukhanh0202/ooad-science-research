package com.uit.ooad.scienceresearch.service.account;

import com.uit.ooad.scienceresearch.dto.account.AccountDto;
import com.uit.ooad.scienceresearch.dto.account.AccountLecturerDto;
import com.uit.ooad.scienceresearch.entity.Account;
import com.uit.ooad.scienceresearch.entity.Lecturer;
import com.uit.ooad.scienceresearch.service.lecturer.IRegisterLecturerService;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
public interface IAccountService {

    IFindAccountService<String, AccountDto> getFindAccountService();

    IRegisterAccountService<AccountLecturerDto, Account> getRegisterAccountService();

}
