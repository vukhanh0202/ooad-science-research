package com.uit.ooad.scienceresearch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class InvalidException extends RuntimeException{
    public InvalidException(String message) {
        super(message);
    }
}