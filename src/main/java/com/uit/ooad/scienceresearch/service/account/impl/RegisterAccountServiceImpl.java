package com.uit.ooad.scienceresearch.service.account.impl;

import com.uit.ooad.scienceresearch.dto.account.AccountLecturerDto;
import com.uit.ooad.scienceresearch.entity.Account;
import com.uit.ooad.scienceresearch.exception.InvalidException;
import com.uit.ooad.scienceresearch.mapper.account.AccountMapper;
import com.uit.ooad.scienceresearch.repository.AccountRepository;
import com.uit.ooad.scienceresearch.service.AbstractBaseService;
import com.uit.ooad.scienceresearch.service.account.IRegisterAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Service
public class RegisterAccountServiceImpl extends AbstractBaseService<AccountLecturerDto, Account>
        implements IRegisterAccountService<AccountLecturerDto, Account> {


    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountMapper accountMapper;

    @Override
    public void preExecute(AccountLecturerDto accountLecturerDto) {

        if (accountLecturerDto.getUsername() == null) {
            throw new InvalidException("Invalid username");
        }
        if (accountRepository.findByUsername(accountLecturerDto.getUsername()).isPresent()) {
            throw new InvalidException("Username is exists");
        }
        if (accountLecturerDto.getPassword() == null) {
            throw new InvalidException("Invalid password");
        }

        if (accountLecturerDto.getRole() == null) {
            throw new InvalidException("Invalid role");
        }
    }

    @Override
    public Account doing(AccountLecturerDto accountLecturerDto) {
        try{
            String pwdBcrypt = BCrypt.hashpw(accountLecturerDto.getPassword(), BCrypt.gensalt(10));
            accountLecturerDto.setPassword(pwdBcrypt);

            return accountRepository.save(accountMapper.toAccount(accountLecturerDto));
        }catch (Exception e){
            return null;
        }
    }
}
