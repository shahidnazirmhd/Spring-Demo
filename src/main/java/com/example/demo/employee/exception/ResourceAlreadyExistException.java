package com.example.demo.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistException extends RuntimeException{
    public ResourceAlreadyExistException(String msg) {
        super(msg);
    }
}
