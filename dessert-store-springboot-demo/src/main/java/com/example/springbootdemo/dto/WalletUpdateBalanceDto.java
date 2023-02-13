package com.example.springbootdemo.dto;

import lombok.Data;

@Data
public class WalletUpdateBalanceDto {
    private int walletId;
    private int balance;
    private int balanceInput;


}
