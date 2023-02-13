package com.example.springbootdemo.dto;

import lombok.Data;

@Data
public class OrderDto {
    private int customerId;
    private String dessertName;
    private int quantity;
    private int orderId;
    private int totalPrice;

}
