package com.naga.spring.dbservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException  {


    public ProductNotFoundException(String errorMessage)
    {

        super(errorMessage);

    }

    public ProductNotFoundException(String errorMessage, Throwable throwable)
    {
        super(errorMessage, throwable);
    }
}
