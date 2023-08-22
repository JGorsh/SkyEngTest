package com.example.errors;


public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }
}
