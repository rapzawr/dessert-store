package com.example.springbootdemo.model;

import lombok.Data;

@Data
public class DessertModel {

    private int dessertId;
    private String dessertName;
    private int price;
    private int  isAvailable;

}
