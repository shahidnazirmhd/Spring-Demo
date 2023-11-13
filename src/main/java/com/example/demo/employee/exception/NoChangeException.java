package com.example.demo.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class NoChangeException extends RuntimeException{
    public NoChangeException(String msg) {
        super(msg);
    }
}
