package com.uit.ooad.scienceresearch.payload;

import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/15/2020
 */
@Data
public class ExceptionResponse {

    private String message;
    private int status;

    public ExceptionResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
