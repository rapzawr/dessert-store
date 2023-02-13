package com.example.springbootdemo.model;

import lombok.Data;

@Data
public class CustomerModel {

    private int customerId;
    private String customerName;
    private int walletId;
    private String address;
    private int contactNo;


}
