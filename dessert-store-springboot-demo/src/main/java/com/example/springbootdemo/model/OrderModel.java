package com.example.springbootdemo.model;

import lombok.Data;

import java.util.Date;

@Data
public class OrderModel {

    private Date orderDate;
    private int orderId;
    private int customerId;
    private int dessertId;
    private int quantity;
    private int totalPrice;
    private int isDelivered;


}
