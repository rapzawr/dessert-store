package com.example.springbootdemo.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private int customerId;
    private String customerName;
    private int walletId;
    private String address;
    private int contactNo;


}
