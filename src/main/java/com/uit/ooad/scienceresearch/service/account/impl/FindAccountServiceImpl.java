package com.uit.ooad.scienceresearch.service.account.impl;

import com.uit.ooad.scienceresearch.dto.account.AccountDto;
import com.uit.ooad.scienceresearch.exception.NotFoundException;
import com.uit.ooad.scienceresearch.mapper.account.AccountMapper;
import com.uit.ooad.scienceresearch.repository.AccountRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.account.IFindAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.uit.ooad.scienceresearch.constant.MessageCode.User.USER_NOT_FOUND;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Service
public class FindAccountServiceImpl extends AbstractBaseService<String, AccountDto>
        implements IFindAccountService<String, AccountDto> {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountMapper accountMapper;

    @Override
    public void preExecute(String username) {
        if (accountRepository.findByUsername(username).isEmpty()) {
            throw new NotFoundException(messageHelper.getMessage(USER_NOT_FOUND, username));
        }
    }

    @Override
    public AccountDto doing(String username) {
        return accountMapper.toAccountDto(accountRepository.findByUsername(username).get());
    }
}
