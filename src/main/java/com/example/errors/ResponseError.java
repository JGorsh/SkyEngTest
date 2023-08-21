package com.example.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class ResponseError {

    private final String status;
    private final String code;
    private final String error;

}
