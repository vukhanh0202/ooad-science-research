package com.uit.ooad.scienceresearch.service;

import com.uit.ooad.scienceresearch.dto.account.AccountDto;
import com.uit.ooad.scienceresearch.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    /**
     * password: password
     * password encrypt
     */
    final String defaultPassword = "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6";

    @Autowired
    IAccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDto accountDto = accountService.getFindAccountService().execute(username);
        if (accountDto.getUsername().equals(username)) {
            return new User(accountDto.getUsername(), accountDto.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
