package com.example.springbootdemo.model;

import lombok.Data;

import java.util.Date;

@Data
public class OrderTransactionHistoryModel {
    int orderId;
    Date orderDate;
    String customerName;
    String dessertName;
    int quantity;
    int totalPrice;
}
