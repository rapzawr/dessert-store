package com.example.springbootdemo.dto;

import lombok.Data;

@Data
public class DessertDto {
    private int dessertId;
    private String dessertName;
    private int price;
    private int isAvailable;


}
