package com.uit.ooad.scienceresearch.config;

import com.uit.ooad.scienceresearch.data.UserPrincipal;
import com.uit.ooad.scienceresearch.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/9/2020
 */
@Slf4j
public class JpaAuditorAware implements AuditorAware<Account> {

    @Override
    public Optional<Account> getCurrentAuditor() {
        UserPrincipal principal;
        try {
            principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e){
            principal=null;
            log.info("User not login");
        }
        if (principal!=null) {
            return Optional.of(new Account(principal.getId()));
        } else {
            return Optional.empty();
        }
    }
}
