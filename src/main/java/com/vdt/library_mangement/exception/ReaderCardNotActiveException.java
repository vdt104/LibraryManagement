package com.vdt.library_mangement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ReaderCardNotActiveException extends RuntimeException {
    public ReaderCardNotActiveException(String message) {
        super(message);
    }
}