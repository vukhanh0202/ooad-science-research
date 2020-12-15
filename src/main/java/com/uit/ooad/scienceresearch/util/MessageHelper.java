package com.uit.ooad.scienceresearch.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/15/2020
 */
@Component
public class MessageHelper {

    /**
     * Message source.
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * Get message by code.
     * @param code code
     * @param param parameters
     * @return message
     */
    public String getMessage(String code, Object... param) {
        return messageSource.getMessage(code,
                param, Locale.US);
    }
}
