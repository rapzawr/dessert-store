package com.example.springbootdemo.dto;

import com.example.springbootdemo.model.OrderModel;
import lombok.Data;

@Data
public class RedisDto {

    private String key;

    private String field;

    private String value;

    private OrderModel orderModel;

    private Long start;

    private Long stop;
}
