package com.example.springbootdemo.exception;

import lombok.Data;

@Data

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(){
        super("Insufficient Balance");
    }
}
