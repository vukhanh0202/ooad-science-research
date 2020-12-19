package com.uit.ooad.scienceresearch.service.account.impl;

import com.uit.ooad.scienceresearch.dto.account.AccountDto;
import com.uit.ooad.scienceresearch.dto.account.AccountLecturerSearchDto;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.account.AccountMapper;
import com.uit.ooad.scienceresearch.repository.AccountRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.account.IFindAccountService;
import com.uit.ooad.scienceresearch.service.account.ISearchAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.uit.ooad.scienceresearch.constant.MessageCode.User.USER_NOT_FOUND;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Service
public class SearchAccountServiceImpl extends AbstractBaseService<String, List<AccountLecturerSearchDto>>
        implements ISearchAccountService<String, List<AccountLecturerSearchDto>> {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountMapper accountMapper;

    @Override
    public List<AccountLecturerSearchDto> doing(String username) {
        return accountMapper.toListAccountLecturerSearchDto(accountRepository.findAllByUsernameContaining(username));
    }
}
