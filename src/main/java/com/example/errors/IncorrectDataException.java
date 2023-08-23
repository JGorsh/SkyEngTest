package com.example.errors;

public class IncorrectDataException extends Exception{

    public IncorrectDataException(String message) {
        super(message);
    }
}
