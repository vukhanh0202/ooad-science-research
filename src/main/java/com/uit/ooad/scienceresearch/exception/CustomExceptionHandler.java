package com.uit.ooad.scienceresearch.exception;

import com.uit.ooad.scienceresearch.payload.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/15/2020
 */
@ControllerAdvice
@Component
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle exception.
     *
     * @param ex      exception
     * @param request request
     * @return ExceptionResponse
     */
    @ExceptionHandler({NotFoundException.class, InvalidException.class, BadRequestException.class})
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex, WebRequest request) {
        HttpStatus httpStatus;
        if (ex instanceof NotFoundException) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (ex instanceof BadRequestException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof InvalidException) {
            httpStatus = HttpStatus.NOT_ACCEPTABLE;
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), httpStatus.value());
        return new ResponseEntity<>(exceptionResponse, httpStatus);
    }
}
