package com.example.springbootdemo.vo;

import lombok.Data;

@Data
public class CustomerVO {
    private int customerId;
    private int walletId;
    private String customerName;
    private String address;
    private int contactNo;

}
