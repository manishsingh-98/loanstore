package com.mss.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErrorResponseException extends RuntimeException{
    public ErrorResponseException(final String message) {
        super(message);
    }
}