package com.example.springbootdemo.vo;

import lombok.Data;

@Data
public class OrderVO {
    private String dessertName;
    private int quantity;
    private int totalPrice;
    private int isDelivered;

}
