package com.pranitpatil.kalah.controller;

import com.pranitpatil.kalah.exception.KalahException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GenericExceptionController {

    private static final Logger logger = LoggerFactory.getLogger(GenericExceptionController.class);
    private static final String ERROR_RESP_TEXT = "Sending error response - ";

    @ExceptionHandler(KalahException.class)
    public @ResponseBody
    ResponseEntity<ErrorResponse> handleKalahException(KalahException exception) {
        logger.error(ERROR_RESP_TEXT, exception);

        ErrorResponse responseBody = new ErrorResponse(exception.getMessage(), exception.getUserMessage());

        return new ResponseEntity<ErrorResponse>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    ResponseEntity<ErrorResponse> handleException(Exception exception) {
        logger.error(ERROR_RESP_TEXT, exception);
        ErrorResponse responseBody = new ErrorResponse(exception.getMessage(),
                "Something went wrong please try again.");

        return new ResponseEntity<ErrorResponse>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
