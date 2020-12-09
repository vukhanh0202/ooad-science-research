package com.uit.ooad.scienceresearch.config;

import com.uit.ooad.scienceresearch.entity.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/9/2020
 */
@Configuration
public class PersistentConfig {

    @Bean(name = "jpaAuditorProvider")
    public AuditorAware<Account> jpaAuditorProvider() {
        return new JpaAuditorAware();
    }
}
