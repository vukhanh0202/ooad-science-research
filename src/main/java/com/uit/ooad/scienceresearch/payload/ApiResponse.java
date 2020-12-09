package com.uit.ooad.scienceresearch.payload;

import lombok.Data;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Data
public class ApiResponse {

    /**
     * Is success.
     */
    private boolean success;

    /**
     * Message response.
     */
    private String message;

    /**
     * Error code.
     */
    private String errorCode;

    /**
     * Data response.
     */
    private Object data;

    public ApiResponse(Object data) {
        this(null, data);
    }

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponse(String message, Object data) {
        this.success = true;
        this.message = message;
        this.data = data;
    }

}