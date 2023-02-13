package com.example.springbootdemo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ServiceException extends RuntimeException {
    private int code;
    private String message;


    }
