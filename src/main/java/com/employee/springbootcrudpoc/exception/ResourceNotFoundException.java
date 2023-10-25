package com.employee.springbootcrudpoc.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends Exception{

    String message ;
    public ResourceNotFoundException(String message) {
        this.message = message;
    }
}
